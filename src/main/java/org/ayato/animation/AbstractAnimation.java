package org.ayato.animation;

import org.ayato.animation.properties.TextProperties;
import org.ayato.system.LunchScene;
import org.ayato.util.Display;

import java.awt.*;
import java.awt.geom.Rectangle2D;
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

    @Override
    public void display(Graphics g) {
        if(bool.getAsBoolean()) {
            if (properties != null)
                properties.runProp(g);
            if(mes != null)
                run(g, mes);
        }
    }
    protected abstract void run(Graphics g, T o);

    public void setViewObject(T mes) {
        this.mes = mes;
    }

    public T getViewObject() {
        return mes;
    }

    public int getX() {
        return x;
    }

    public void setX(int i) {
        x = i;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}