package org.ayato.animation;

import org.ayato.animation.image.ImageMaker;
import org.ayato.animation.text.properties.IProperty;
import org.ayato.util.LastRunningProperty;

import java.awt.*;

public class FadeIn extends TimeConverter implements LastRunningProperty {
    private boolean isEnd = false;
    public FadeIn(long maxTime) {
        super(maxTime, 255);
    }

    @Override
    protected void clockTick(Graphics g, Properties properties, Animation<?> animation, int secTime) {
        Color color = g.getColor();
        switch (animation.mes){
            case String s ->  g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), secTime));
            default -> throw new IllegalStateException("Unexpected value: " + animation.mes);
        }
        if(animation.mes instanceof String) {
            g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), secTime));
        }
        if(secTime >= 255){
            isEnd = true;
        }
    }

    @Override
    public boolean isEnd() {
        return isEnd;
    }
}
