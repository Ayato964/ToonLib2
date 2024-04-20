package org.ayato.animation;

import org.ayato.animation.text.properties.*;
import org.ayato.util.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Properties<T> implements DisplayAnimation<T>{
    public int baseX, baseY, rx, ry;
    public Position position;
    protected final ArrayList<Supplier<IProperty>> properties;
    protected final ArrayList<IProperty> init_properties;
    public BooleanSupplier isVisible;
    protected Properties()
    {
        this(0, 0);
    }

    public Properties(int x, int y) {
        properties = new ArrayList<>();
        init_properties = new ArrayList<>();
        baseX = x;
        baseY = y;
        rx = baseX;
        ry = baseY;
        position = new Position(()->baseX, ()-> baseY, 0, 0);
    }
    public void runProp(Graphics g, Animation<T> animation){
        if(isVisible.getAsBoolean()) {
            for (IProperty p : init_properties)
                p.runningProperty(g, this, animation);
        }
    }
    public void reset(){
        init();
    }

    public void init() {
        init_properties.clear();
        baseX = rx;
        baseY = ry;
        isVisible = isVisible != null ? isVisible : ()->true;
        for(Supplier<IProperty> sup : properties){
            init_properties.add(sup.get());
        }
    }
}
