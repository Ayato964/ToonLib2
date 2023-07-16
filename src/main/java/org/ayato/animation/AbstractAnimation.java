package org.ayato.animation;

import org.ayato.animation.properties.TextProperties;
import org.ayato.system.LunchScene;
import org.ayato.util.Display;

import java.util.function.BooleanSupplier;

public abstract class AbstractAnimation<T> implements Display {
    protected T mes;
    protected int x, y;
    public final LunchScene MASTER;
    protected TextProperties properties;
    public BooleanSupplier bool;

    protected AbstractAnimation(LunchScene master) {
        MASTER = master;
    }
}
