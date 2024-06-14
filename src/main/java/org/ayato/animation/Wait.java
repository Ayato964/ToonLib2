package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;

import java.awt.*;
import java.util.function.BooleanSupplier;

public class Wait implements IProperty {
    private final BooleanSupplier isWaitCondition;

    public Wait(BooleanSupplier isWaitCondition) {
        this.isWaitCondition = isWaitCondition;
    }

    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {

    }

    @Override
    public boolean isEnd() {
        return isWaitCondition.getAsBoolean();
    }
}
