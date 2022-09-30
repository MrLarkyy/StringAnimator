package cz.larkyy.stringanimator.api.animation;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Consumer;

public class Animation {
    private final String id;
    private boolean loop = false;
    private final List<Frame> frames;

    public Animation(String id, List<Frame> frames) {
        this.id = id;
        this.frames = frames;
    }

    public void addFrame(Frame frame) {
        frames.add(frame);
    }

    public void addFrame(int index, Frame frame) {
        frames.add(index,frame);
    }

    public void removeFrame(int index) {
        frames.remove(index);
    }

    public void removeFrame(Frame frame) {
        frames.remove(frame);
    }

    public void setLoop(boolean bool) {
        loop = bool;
    }

    public boolean isLooped() {
        return loop;
    }

    public String getId() {
        return id;
    }

    public StartedAnimation play(Player player, Consumer<String> consumer, Runnable callback) {
        StartedAnimation startedAnimation = new StartedAnimation(this,player,consumer,callback);
        play(startedAnimation,0);
        return startedAnimation;
    }

    public void play(StartedAnimation animation, int index) {
        if (animation.isSkipped()) {
            return;
        }

        if (index >= frames.size()) {
            if (loop) {
                animation.getConsumer().accept(frames.get(0).play(
                    () -> play(animation,0))
                );
            } else {
                animation.getCallback().run();
            }
            return;
        }
        animation.getConsumer().accept(frames.get(index).play(
                () -> play(animation,index+1))
        );
    }
}
