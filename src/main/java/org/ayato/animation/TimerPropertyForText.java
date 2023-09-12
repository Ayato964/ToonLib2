package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;

import java.awt.*;

public abstract class TimerPropertyForText implements IProperty {
    private final double time;
    private final AnimationSequentialOrder ORDER;

    public TimerPropertyForText(AnimationSequentialOrder o, double timer){
        time = timer;
        ORDER = o;
    }

    @Override
    public void setup(Graphics g, Properties properties, Animation<?> animation) {
        Thread t = new Thread(()->runSetup(g, properties, animation));
        t.start();
    }

    private void runSetup(Graphics g, Properties properties, Animation<?> animation){
        while (getLoopRule()){
            try {
                Thread.sleep(setWait(time));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            run(g, properties, (Animation<String>) animation);
        }

        if (ORDER != null)
            endAnimation(ORDER);

    }
    protected long setWait(double time){
        return (long) (time * 1000L);
    }

    protected abstract void run(Graphics g, Properties properties, Animation<String> animation);
    protected abstract boolean getLoopRule();
}
