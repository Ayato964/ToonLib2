package org.ayato;

import org.ayato.animation.AbstractAnimation;
import org.ayato.animation.properties.IProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public abstract class AbstractProperties<T extends AbstractAnimation<?>> {
    protected final ArrayList<IProperty> properties;
    protected T animation;
    protected BooleanSupplier booleanSupplier;
    protected AbstractProperties(){
        properties = new ArrayList<>();
    }
    public void addAnimation(T text){
        animation = text;
    }

    public void runProp(Graphics g){
        if(booleanSupplier != null) {
            animation.bool = booleanSupplier;
            booleanSupplier = null;
            return;
        }
        for(IProperty<T, AbstractProperties<T>> p : properties)
            p.runningProperty(g, this, animation);
    }
}
