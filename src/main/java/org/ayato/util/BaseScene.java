package org.ayato.util;

import org.ayato.system.ToonMaster;
import org.ayato.system.Tick;
import org.ayato.component.ToonObject;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class BaseScene implements Setup, Display, Tick {
    private final long serial = new Random().nextLong(0, 1000000);
    private final CopyOnWriteArrayList<ToonObject> objects = new CopyOnWriteArrayList<>();
    public final void setToonObjectClass(ToonMaster scene){
        setToonObjects(objects);
        for(ToonObject o : objects){
            scene.SCENE.addDisplay(o);
            scene.TICK.add(o);
        }
    }

    public final void addObject(ToonObject toonObject){
        objects.add(toonObject);
        ToonMaster.getINSTANCE().SCENE.addDisplay(toonObject);
        ToonMaster.getINSTANCE().TICK.add(toonObject);
    }
    public final void  deleteObject(ToonObject object){
        objects.remove(object);
        ToonMaster.getINSTANCE().SCENE.removeDisplay(object);
        ToonMaster.getINSTANCE().TICK.remove(object);
    }
    @Override
    public long getSerialID() {
        return serial;
    }

    protected void setToonObjects(CopyOnWriteArrayList<ToonObject> to) {}
}
