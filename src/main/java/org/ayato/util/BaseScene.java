package org.ayato.util;

import org.ayato.system.LunchScene;
import org.ayato.system.Tick;
import org.ayato.system.ToonObject;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class BaseScene implements Setup, Display, Tick {
    private final long serial = new Random().nextLong(0, 1000000);
    private final CopyOnWriteArrayList<ToonObject> objects = new CopyOnWriteArrayList<>();
    public final void setToonObjectClass(LunchScene scene){
        setToonObjects(objects);
        for(ToonObject o : objects){
            scene.SCENE.addDisplay(o);
            scene.TICK.add(o);
        }
    }

    protected final void addObject(ToonObject toonObject){
        objects.add(toonObject);
        LunchScene.getINSTANCE().SCENE.addDisplay(toonObject);
        LunchScene.getINSTANCE().TICK.add(toonObject);
    }
    protected final void  deleteObject(ToonObject object){
        objects.remove(object);
        LunchScene.getINSTANCE().SCENE.removeDisplay(object);
        LunchScene.getINSTANCE().TICK.remove(object);
    }
    @Override
    public long getSerialID() {
        return serial;
    }

    protected void setToonObjects(CopyOnWriteArrayList<ToonObject> to) {}
}
