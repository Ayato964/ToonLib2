package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;
import org.ayato.util.LastRunningProperty;

import java.awt.*;

public class FadeIn extends TimeConverter implements LastRunningProperty {
    public FadeIn(long maxTime) {
        super(maxTime, 255);
    }

    @Override
    protected void clockTick(Graphics g, Properties properties, Animation<?> animation, int secTime) {
        Color color = g.getColor();
        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), secTime));
    }
}
