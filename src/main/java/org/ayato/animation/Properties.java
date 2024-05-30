package org.ayato.animation;

import org.ayato.animation.text.properties.*;
import org.ayato.util.LastRunningProperty;
import org.ayato.util.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

public sealed abstract class Properties<T> implements DisplayAnimation<T> permits TextProperties, ImageProperties{
    public int rx, ry;
    public Position position;
    protected final ArrayList<Supplier<IProperty>> properties;
    protected final ArrayList<IProperty> init_properties;
    public BooleanSupplier isVisible;
    public boolean isFirst = true;
    protected Properties()
    {
        this(0, 0);
    }

    public Properties(int x, int y) {
        properties = new ArrayList<>();
        init_properties = new ArrayList<>();
        rx = x;
        ry = y;
        position = new Position(x,y, 0, 0);
    }
    public void runProp(Graphics g, Animation<T> animation){
        if(isVisible.getAsBoolean()) {
            for (IProperty p : init_properties)
                p.runningProperty(g, this, animation);
            if(isFirst) {
                for (IProperty p : init_properties)
                    p.setupProperty(g, this, animation);
                isFirst = false;
            }
        }

    }
    public void reset(){
        init();
    }
    public Properties<T> custom(Supplier<IProperty> property){
        properties.add(property);
        return this;
    }

    public void init() {
        init_properties.clear();
        position.setX(rx);
        position.setY(ry);
        isVisible = isVisible != null ? isVisible : ()->true;
        ArrayList<LastRunningProperty> last = new ArrayList<>();
        for(Supplier<IProperty> sup : properties){
            IProperty s = sup.get();
            if(s instanceof LastRunningProperty l){
                last.add(l);
            }else {
                init_properties.add(s);
            }
        }
        for(LastRunningProperty l : last){
            init_properties.add((IProperty) l);
        }

    }
}
