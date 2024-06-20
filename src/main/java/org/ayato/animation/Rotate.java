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
        og.rotate(rad, 10 + (double) og.getFontMetrics().stringWidth((String) animation.mes) / 2,
                (double) g.getFontMetrics().getHeight()+ (double) g.getFontMetrics().getHeight() / 2);
        /*
        og.fillOval(10 +  og.getFontMetrics().stringWidth((String) animation.mes) / 2,
                 g.getFontMetrics().getHeight() + g.getFontMetrics().getHeight() / 2, 10, 10);

         */
    }

}
