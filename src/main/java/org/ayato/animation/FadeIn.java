package org.ayato.animation;

import org.ayato.animation.image.ImageMaker;
import org.ayato.animation.text.properties.IProperty;
import org.ayato.util.LastRunningProperty;

import java.awt.*;

public class FadeIn extends TimeConverter implements LastRunningProperty {
    public FadeIn(long maxTime) {
        super(maxTime);
    }

    @Override
    protected void clockTick(Graphics2D g, Properties properties, Animation<?> animation, double progress) {
        Color color = g.getColor();
        switch (animation.mes){
            case String s ->  g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (255 * progress)));
            default -> throw new IllegalStateException("Unexpected value: " + animation.mes);
        }
     }

}
