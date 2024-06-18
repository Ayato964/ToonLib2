package org.ayato.animation;

import org.ayato.animation.text.properties.*;
import org.ayato.component.Transform;
import org.ayato.util.LastRunningProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public sealed abstract class Properties<T> implements DisplayAnimation<T> permits TextProperties, ImageProperties{
    public int rx, ry;
    public Transform transform;
    protected final ArrayList<Supplier<IProperty>> properties;
    protected final ArrayList<IProperty> init_properties;
    public BooleanSupplier isVisible;
    public boolean isFirst = true;
    protected Properties()
    {
        this(new Transform(0, 0, 0, 0));
    }

    public Properties(Transform transform) {
        properties = new ArrayList<>();
        init_properties = new ArrayList<>();
        rx = transform.position.getNormalX();
        ry = transform.position.getNormalY();
        this.transform = transform;
    }
    public void runProp(Graphics2D g2, Graphics g, Animation<T> animation){
        if(isVisible.getAsBoolean()) {
            for (IProperty p : init_properties)
                p.runningProperty(g2, g, this, animation);
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
        transform.position.setX(rx);
        transform.position.setY(ry);
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
