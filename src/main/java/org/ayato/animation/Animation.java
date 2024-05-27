package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;
import org.ayato.system.BaseAbstractObject;
import org.ayato.system.ComponentTag;
import org.ayato.system.LunchScene;
import org.ayato.util.Display;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public class Animation<T> extends BaseAbstractObject {
    public T mes;
    public final LunchScene MASTER;
    protected Properties<T> properties;

    public Animation(LunchScene master, T a, Properties<T> prop){
        mes = a;
        MASTER = master;
        properties = prop;
    }

    @Override
    public void display(Graphics g) {
            if (properties != null)
                properties.runProp(g, this);
            if(mes != null)
                properties.run(MASTER,g, mes);
    }
    public <A extends IProperty> A getPropertyClass(Class<A> cls){
        A a = null;
        for (IProperty p : properties.init_properties){
            if(cls.isInstance(p)){
                a = (A) p;
            }
        }
        return a;
    }

    public void setViewObject(T mes) {
        this.mes = mes;
    }

    public T getViewObject() {
        return mes;
    }

    public void init(){
        properties.init();
    }
}
