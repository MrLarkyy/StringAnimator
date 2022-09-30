package cz.larkyy.stringanimator.api.animation;

import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class StartedAnimation {

    private final Animation animation;
    private final Consumer<String> consumer;
    private final Runnable callback;
    private final Player player;
    private boolean skipped = false;


    public StartedAnimation(Animation animation, Player player, Consumer<String> consumer, Runnable callback) {
        this.animation = animation;
        this.player = player;
        this.consumer = consumer;
        this.callback = callback;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void skip() {
        callback.run();
        skipped = true;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public Consumer<String> getConsumer() {
        return consumer;
    }

    public Player getPlayer() {
        return player;
    }

    public Runnable getCallback() {
        return callback;
    }
}
