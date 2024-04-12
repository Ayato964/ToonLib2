package org.ayato.animation;

import org.ayato.animation.text.properties.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Properties<T> implements DisplayAnimation<T>{
    public int mx, my;
    public int x, y;
    protected final ArrayList<Supplier<IProperty>> properties;
    protected final ArrayList<IProperty> init_properties;
    public BooleanSupplier isVisible;
    private boolean isFirst = true;
    protected Properties()
    {
        this(0, 0);
    }

    public Properties(int x, int y) {
        properties = new ArrayList<>();
        init_properties = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.mx = x;
        this.my = y;
    }
    public void runProp(Graphics g, Animation<T> animation){
        if(isVisible.getAsBoolean()) {
            if (isFirst) {
                for (IProperty p : init_properties)
                    p.setup(g, this, animation);
                isFirst = false;
            }

            for (IProperty p : init_properties)
                p.runningProperty(g, this, animation);
        }
    }
    public Properties<?> setSize(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }
    public void setPosition(int x, int y){
        mx = x;
        my = y;
        this.x = x;
        this.y = y;
    }
    public Properties<?> setX(int i) {
        x = i;
        return this;
    }

    public Properties<?> setY(int y) {
        this.y = y;
        return this;
    }


    public int getY() {
        return y;
    }

    public Properties<?> getInstance(){
        return this;
    }
    public void reset(){
        x = mx;
        y = my;
        init();
    }

    public void init() {
        init_properties.clear();
        isVisible = isVisible != null ? isVisible : ()->true;
        for(Supplier<IProperty> sup : properties){
            init_properties.add(sup.get());
        }
    }

    public AnimationSequentialOrder popMatrix(){
        AnimationSequentialOrder order = new AnimationSequentialOrder(this);
        properties.add(()->order);
        return order;
    }
}
