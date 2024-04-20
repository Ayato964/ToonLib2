package org.ayato.animation;

import org.ayato.system.LunchScene;
import org.ayato.util.Display;

import java.awt.*;
import java.util.function.BooleanSupplier;

public class Animation<T> implements Display {
    protected T mes;
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
