package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;

import java.awt.*;

public class FadeIn extends TimerPropertyForText {
    private boolean isTimeCount = false;
    private int a = 0;
    public FadeIn(AnimationSequentialOrder o, long time){
        super(o, time);
    }
    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
        if (isTimeCount){
            Color c = g.getColor();
            g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), a));
            isTimeCount = false;
    }

    }
    @Override
    public void reset(int nx, int ny) {}

    @Override
    protected void run(Graphics g, Properties properties, Animation<String> animation) {
        isTimeCount = true;
        a ++;
    }

    @Override
    protected boolean getLoopRule() {
        return a <= 255;
    }

    @Override
    protected long setWait(double time) {
        return (long) (time * 1000L / 255);
    }
}
