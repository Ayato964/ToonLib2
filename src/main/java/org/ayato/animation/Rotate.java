package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;

import java.awt.*;

public class Rotate implements IProperty {
    private final double rad;
    public Rotate(int rotate){
        rad = Math.toRadians(rotate);
    }
    @Override
    public void runningProperty(Graphics2D og, Graphics g, Properties properties, Animation<?> animation) {
        og.rotate(rad, (double) animation.baseGraphics.getWidth() / 2 - (double) og.getFontMetrics().stringWidth((String) animation.baseObject.get()) / 2,
                (double) animation.baseGraphics.getHeight() / 2 + og.getFontMetrics().getHeight());
        /*
        og.fillOval(animation.baseGraphics.getWidth() / 2,
                 animation.baseGraphics.getHeight() / 2 + og.getFontMetrics().getHeight() / 2, 10, 10);

         */
    }

}
