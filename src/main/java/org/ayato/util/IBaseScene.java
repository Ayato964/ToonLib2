package org.ayato.util;

import org.ayato.system.LunchScene;
import org.ayato.system.Tick;
import org.ayato.system.ToonObject;

import java.util.ArrayList;
import java.util.Random;

public interface IBaseScene extends Setup, Display, Tick {
    public int SerialID = new Random().nextInt(100000, 10000000);
    public default IBaseScene getInstance(){
        return this;
    }

    public default void setToonObjectClass(LunchScene scene){
        ArrayList<ToonObject> objects = new ArrayList<>();
        setToonObjects(objects);
        for(ToonObject o : objects){
            scene.SCENE.addDisplay(o);
            scene.TICK.add(o);
        }
    }
    default void setToonObjects(ArrayList<ToonObject> to){}
}
