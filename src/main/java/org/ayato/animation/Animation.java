package org.ayato.animation;

import org.ayato.system.LunchScene;
import org.ayato.util.Display;

import java.awt.*;
import java.util.function.BooleanSupplier;

public class Animation<T> implements Display {
    protected T mes;
    public final LunchScene MASTER;
    protected Properties<?> properties;
    public BooleanSupplier bool;
    public DisplayAnimation<T> displayAnimation;

    protected Animation(LunchScene master, BooleanSupplier bool){
        MASTER = master;
        this.bool = bool;
    }
    public static <T> Animation<T> create(LunchScene scene, T t, Properties<T> properties){
        Animation<T> i = new Animation<>(scene, ()->true);
        i.mes = t;
        if(properties != null) {
            i.properties = properties;
            properties.addAnimation(i);
        }

        scene.SCENE.addDisplay(i);
        return i;
    }

    @Override
    public void display(Graphics g) {
        if(bool.getAsBoolean()) {
            if (properties != null)
                properties.runProp(g);
            if(mes != null && displayAnimation != null)
                displayAnimation.run(MASTER, g, mes);
        }
    }

    public void setViewObject(T mes) {
        this.mes = mes;
    }

    public T getViewObject() {
        return mes;
    }
}
