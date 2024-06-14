package org.ayato.animation;

import java.awt.*;
import java.util.function.BooleanSupplier;

public class FadeOut extends TimeConverter{
    private final BooleanSupplier condition;
    public FadeOut(long maxTime, BooleanSupplier condition) {
        super(maxTime);
        this.condition = condition;
    }

    @Override
    protected void clockTick(Graphics g, Properties properties, Animation<?> animation, double secTime) {
        if (condition.getAsBoolean()) {
            switch (animation.mes) {
                case String s -> {
                    Color now = g.getColor();
                    g.setColor(new Color(now.getRed(), now.getGreen(), now.getBlue(), Math.max(0, (int) (255 - 255 * secTime))));
                }
                default -> throw new IllegalStateException("Unexpected value: " + animation.mes);
            }
        }
    }
}
