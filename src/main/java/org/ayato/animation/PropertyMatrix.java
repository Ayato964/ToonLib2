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
    public void setupProperty(Graphics2D o2, Graphics g, Properties<?> properties, Animation<?> animation) {
        next(o2, g, properties, animation);
    }

    @Override
    public void runningProperty(Graphics2D og, Graphics g, Properties properties, Animation<?> animation) {
        if(init_properties != null) {
            init_properties.runningProperty(og, g, properties, animation);
            if (init_properties.isEnd()) {
                next(og,g, properties, animation);
            }
        }
    }
    private void next(Graphics2D g2, Graphics g, Properties<?> properties, Animation<?> animation){
        if(runningNextIndex < this.properties.size()) {
            init_properties = this.properties.get(runningNextIndex).get();
            init_properties.setupProperty(g2, g, properties, animation);
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
    public PropertyMatrix<V> move(int x, int y){
        properties.add(()->new Move(x, y));
        return this;
    }
    public PropertyMatrix<V> moveTo(int x, int y, long second, MoveTo.VelocityFormat format){
        properties.add(()->new MoveTo(x, y, second, format));
        return this;
    }
    public PropertyMatrix<V> rotate(int rad){
        properties.add(()->new Rotate(rad));
        return this;
    }
    public PropertyMatrix<V> rotateTo(int r, long time){
        properties.add(()-> new RotateTo(r, time));
        return this;
    }

    public PropertyMatrix<V> sleep(long time){
        properties.add(()->new Sleep(time));
        return this;
    }
    public PropertyMatrix<V> wait(BooleanSupplier isWaitCondition){
        properties.add(()->new Wait(isWaitCondition));
        return this;
    }

    public V endMatrix(){
        return MASTER;
    }

}
