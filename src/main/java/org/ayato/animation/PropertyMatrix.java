package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class PropertyMatrix<V extends Properties<?>> implements IProperty {
    private final ArrayList<Supplier<IProperty>> properties = new ArrayList<>();
    private IProperty init_properties;
    private int runningNextIndex = 0;
    private final V MASTER;
    public PropertyMatrix(V master_properties){
        MASTER = master_properties;
    }

    @Override
    public void setupProperty(Graphics g, Properties<?> properties, Animation<?> animation) {
        next(g, properties, animation);
    }

    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
        if(init_properties != null) {
            init_properties.runningProperty(g, properties, animation);
            if (init_properties.isEnd()) {
                next(g, properties, animation);
            }
        }
    }
    private void next(Graphics g, Properties<?> properties, Animation<?> animation){
        if(runningNextIndex < this.properties.size()) {
            init_properties = this.properties.get(runningNextIndex).get();
            init_properties.setupProperty(g, properties, animation);
            runningNextIndex++;
        }
    }
    public PropertyMatrix<V> fadeIn(long time){
        properties.add(()->new FadeIn(time));
        return this;
    }
    public PropertyMatrix<V> fadeOut(long time){
        return fadeOut(time, ()->true);
    }
    public PropertyMatrix<V> fadeOut(long time, BooleanSupplier condition){
        properties.add(()->new FadeOut(time, condition));
        return this;
    }

    public V endMatrix(){
        return MASTER;
    }

}
