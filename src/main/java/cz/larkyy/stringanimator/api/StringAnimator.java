package cz.larkyy.stringanimator.api;

import cz.larkyy.stringanimator.api.animation.AnimationManager;

public abstract class StringAnimator {
    public static StringAnimator api;
    private AnimationManager animationManager;

    public AnimationManager getAnimationManager() {
        return animationManager;
    }

    public void setAnimationManager(AnimationManager animationManager) {
        this.animationManager = animationManager;
    }
}
