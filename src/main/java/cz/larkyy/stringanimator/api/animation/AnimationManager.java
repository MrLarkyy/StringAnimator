package cz.larkyy.stringanimator.api.animation;

import cz.larkyy.stringanimator.StringAnimatorPluginImpl;
import cz.larkyy.stringanimator.Config;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class AnimationManager {

    private Map<String, Animation> animations = new HashMap<>();
    private final Map<Player,AnimationList> players = new HashMap<>();

    public void loadAnimations() {
        new BukkitRunnable() {
            @Override
            public void run() {
                animations = new HashMap<>();
                Config config = new Config(StringAnimatorPluginImpl.plugin,"animations.yml");
                config.load();

                if (!config.getConfiguration().contains("animations")) {
                    return;
                }
                for (String str : config.getConfiguration().getConfigurationSection("animations").getKeys(false)) {
                    loadAnimation(config.getConfiguration(),str);
                }
            }
        }.runTaskAsynchronously(StringAnimatorPluginImpl.plugin);
    }

    private void loadAnimation(FileConfiguration cfg, String id) {
        String path = "animations."+id;
        List<Frame> frames = new ArrayList<>();

        boolean loop = cfg.getBoolean(path+".loop",false);

        if (!cfg.contains(path+".frames")) {
            return;
        }

        for (String s : cfg.getConfigurationSection(path+".frames").getKeys(false)) {
            String value = cfg.getString(path+".frames."+s+".value","");
            int length = cfg.getInt(path+".frames."+s+".length",10);
            frames.add(new Frame(value,length));
        }

        Animation animation = new Animation(id,frames);
        animation.setLoop(loop);

        animations.put(id,animation);
    }

    private void addPlayer(StartedAnimation animation) {
        if (players.containsKey(animation.getPlayer())) {
            AnimationList list = players.get(animation.getPlayer());
            list.addAnimation(animation);
        } else {
            players.put(animation.getPlayer(),new AnimationList(animation));
        }
    }

    public boolean isInAnimation(Player player) {
        return players.containsKey(player);
    }

    public boolean playAnimation(String animationId, Player player, Consumer<String> consumer, Runnable callback) {
        Animation animation = animations.get(animationId);
        if (animation == null) {
            return false;
        }

        AtomicReference<StartedAnimation> startedAnimationAtomicReference = new AtomicReference<>();
        StartedAnimation startedAnimation = animation.play(player,consumer, () -> {
            players.get(player).removeAnimation(startedAnimationAtomicReference.get(), () ->
                    players.remove(player)
            );
            callback.run();
        });
        startedAnimationAtomicReference.set(startedAnimation);

        addPlayer(
            startedAnimation
        );
        return true;
    }
}
