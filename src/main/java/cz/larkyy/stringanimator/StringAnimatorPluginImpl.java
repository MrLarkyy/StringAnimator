package cz.larkyy.stringanimator;

import cz.larkyy.stringanimator.api.StringAnimator;
import cz.larkyy.stringanimator.api.StringAnimatorPlugin;

public class StringAnimatorPluginImpl extends StringAnimatorPlugin {

    private static StringAnimator api;

    @Override
    public void onEnable() {
        plugin = this;
        api = StringAnimatorImpl.initialize(this);
        api.getAnimationManager().loadAnimations();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static StringAnimator api() {
        return api;
    }
}