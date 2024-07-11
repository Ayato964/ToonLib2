package org.ayato.util;

import org.ayato.system.ComponentGroup;
import org.ayato.system.ToonMaster;
import org.ayato.system.Tick;
import org.ayato.component.ToonObject;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class BaseScene implements Setup, Display, Tick {
    private final long serial = new Random().nextLong(0, 1000000);
    private final CopyOnWriteArrayList<ToonObject> objects = new CopyOnWriteArrayList<>();

    private String groupID;
    private ArrayList<String> tags = new ArrayList<>();


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

    public CopyOnWriteArrayList<ToonObject> getObjects() {
        return objects;
    }
    protected void setToonObjects(CopyOnWriteArrayList<ToonObject> to) {}

    @Override
    public ComponentGroup setGroup(String str) {
        groupID = str;
        return this;
    }

    @Override
    public String getGroup() {
        return groupID;
    }

    @Override
    public ArrayList<String> getTagsList() {
        return tags;
    }

}
