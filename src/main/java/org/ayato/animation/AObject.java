package org.ayato.animation;

import org.ayato.system.LunchScene;

import java.awt.*;

public abstract class AObject<T> implements DisplayAnimation<T>{
    private T OBJECT;
    public AObject(T o){
        OBJECT = o;
    }

    public T getOBJECT() {
        return OBJECT;
    }

    public void setOBJECT(T OBJECT) {
        this.OBJECT = OBJECT;
    }

}
