package org.ayato.animation;

import java.awt.*;
import java.util.function.BooleanSupplier;

public class FadeOut extends TimeConverter{
    private final BooleanSupplier condition;
    public FadeOut(long maxTime, BooleanSupplier condition) {
        super(maxTime, 255);
        this.condition = condition;
    }

    @Override
    protected void clockTick(Graphics g, Properties properties, Animation<?> animation, int secTime) {
        if(condition.getAsBoolean()) {
            switch (animation.mes) {
                case String s -> {
                    Color now = g.getColor();
                    g.setColor(new Color(now.getRed(), now.getGreen(), now.getBlue(), 255 - secTime));
                }
                default -> throw new IllegalStateException("Unexpected value: " + animation.mes);
            }
        }
    }
}
