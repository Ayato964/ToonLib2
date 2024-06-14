package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;

import java.awt.*;

public abstract class TimeConverter implements IProperty {
    private final long maxTime;
    private long time = 0;
    public TimeConverter(long maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public final void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
        double progress = (double) time / maxTime;
        clockTick(g, properties, animation, progress);
        if(time <= maxTime)
            time++;
    }

    protected abstract void clockTick(Graphics g, Properties properties, Animation<?> animation, double progress);

    @Override
    public boolean isEnd() {
        return time >= maxTime;
    }
}
