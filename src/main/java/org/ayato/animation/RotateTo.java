package org.ayato.animation;

import org.ayato.animation.image.ImageMaker;

import java.awt.*;
import java.util.IllegalFormatWidthException;

public class RotateTo extends TimeConverter{
    final double rad;
    double radNow = 0;
    public RotateTo(int rad, long maxTime) {
        super(maxTime);
        this.rad = Math.toRadians(rad);

    }

    @Override
    protected void clockTick(Graphics2D g, Properties properties, Animation<?> animation, double progress) {
        radNow = rad * progress;
        switch (animation.baseObject.get()){
            case String as->g.rotate(radNow, (double) animation.baseGraphics.getWidth() / 2 - (double) g.getFontMetrics().stringWidth((String) animation.baseObject.get()) / 2,
                    (double) animation.baseGraphics.getHeight() / 2 + g.getFontMetrics().getHeight());
            case ImageMaker im -> g.rotate(rad);
            default -> throw new IllegalStateException("Don't know type class of Animation.java");
        }
    }
}
