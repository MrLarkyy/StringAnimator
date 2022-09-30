package cz.larkyy.stringanimator.api.animation;

import cz.larkyy.stringanimator.StringAnimatorPluginImpl;
import org.bukkit.scheduler.BukkitRunnable;

public class Frame {

    private final int length;
    private final String string;

    public Frame(String string, int length) {
        this.length = length;
        this.string = string;
    }

    public String play(Runnable callback) {
        new BukkitRunnable() {
            @Override
            public void run() {
                callback.run();
            }
        }.runTaskLater(StringAnimatorPluginImpl.plugin,length);
        return string;
    }

}
