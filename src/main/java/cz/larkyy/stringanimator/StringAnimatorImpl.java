package cz.larkyy.stringanimator;

import cz.larkyy.stringanimator.api.StringAnimator;
import cz.larkyy.stringanimator.api.StringAnimatorPlugin;
import cz.larkyy.stringanimator.api.animation.AnimationManager;
import org.bukkit.plugin.java.JavaPlugin;

public class StringAnimatorImpl extends StringAnimator {

    public static StringAnimator initialize(JavaPlugin plugin) {
        if (api != null) {
            return api;
        }

        api = new StringAnimatorImpl();
        if (StringAnimatorPlugin.plugin == null) {
            StringAnimatorPlugin.plugin = plugin;
        }
        api.setAnimationManager(new AnimationManager());
        return api;
    }

}
