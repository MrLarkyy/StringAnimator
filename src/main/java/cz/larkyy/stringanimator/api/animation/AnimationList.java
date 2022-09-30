package cz.larkyy.stringanimator.api.animation;

import java.util.*;

public class AnimationList {

    private final List<StartedAnimation> animations;

    public AnimationList() {
        animations = new ArrayList<>();
    }

    public AnimationList(StartedAnimation startedAnimation) {
        animations = new ArrayList<>();
        animations.add(startedAnimation);
    }

    public void addAnimation(StartedAnimation animation) {
        animations.add(animation);
    }

    public void removeAnimation(StartedAnimation startedAnimation, Runnable callback) {
        animations.remove(startedAnimation);
        if (animations.isEmpty()) {
            callback.run();
        }
    }

}
