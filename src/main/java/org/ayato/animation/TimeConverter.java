package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;

import java.awt.*;

public abstract class TimeConverter implements IProperty {
    private long count_time = 0L;
    private float overTime;
    private int secTime = 0;
    private int maxValue;

    public TimeConverter(long maxTime, int value) {
        overTime = (float) value / maxTime; //increase
        maxValue = value;
    }

    @Override
    public void setupProperty(Graphics g, Properties<?> properties, Animation<?> animation) {
        overTime = overTime * animation.MASTER.SCENE.SLEEP_TIME;
    }

    @Override
    public final void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
        if(secTime <= maxValue) {
            count_time += 1;
            secTime = (int) Math.min(maxValue, overTime * count_time);
        }
        clockTick(g, properties, animation, secTime);
    }

    protected abstract void clockTick(Graphics g, Properties properties, Animation<?> animation, int secTime);
}
