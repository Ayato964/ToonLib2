package org.ayato.animation;

import org.ayato.animation.text.properties.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BooleanSupplier;

public abstract class Properties{
    public int mx, my;
    public int x, y;
    protected final ArrayList<IProperty> properties;
    protected Animation<?> animation;
    protected BooleanSupplier booleanSupplier;
    protected Properties()
    {
        this(0, 0);
    }

    public Properties(int x, int y) {
        properties = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.mx = x;
        this.my = y;
    }

    public void addAnimation(Animation<?> text){
        animation = text;
    }



    public void runProp(Graphics g){
        if(booleanSupplier != null) {
            animation.bool = booleanSupplier;
            booleanSupplier = null;
            return;
        }
        for(IProperty p : properties)
            p.runningProperty(g, this, animation);
    }
    public Properties setSize(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }

    public Properties setX(int i) {
        x = i;
        return this;
    }

    public Properties setY(int y) {
        this.y = y;
        return this;
    }


    public int getY() {
        return y;
    }

    public Properties getInstance(){
        return this;
    }
    public void reset(){
        x = mx;
        y = my;
        for(IProperty p : properties){
            p.reset(mx, my);
        }
    }

    public <M extends Properties> M copy() {
        Properties t = this instanceof TextProperties
                ?  new TextProperties(x, y)
                :  new ImageProperties(x, y, 0, 0);
        t.properties.addAll((Collection<? extends IProperty>) properties.clone());
        return (M) t;
    }

}
