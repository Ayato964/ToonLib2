package org.ayato.util;

import org.ayato.system.LunchScene;
import org.ayato.system.Tick;
import org.ayato.system.ToonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public abstract class IBaseScene implements Setup, Display, Tick {
    private final long serial = new Random().nextLong(0, 1000000);
    public final void setToonObjectClass(LunchScene scene){
        ArrayList<ToonObject> objects = getToonObjects();

        try {
            setToonObjects(objects);
        } catch (NullPointerException e) {
            throw new NullPointerException("You're setting ToonObjects. But You don't declared container.\n " +
                    "You need to declare container with getToonObjects().");
        }
        if(objects != null)
            for(ToonObject o : objects){
                scene.SCENE.addDisplay(o);
                scene.TICK.add(o);
            }
    }
    public void setToonTick(LunchScene scene){
        scene.TICK.add(new Tick() {
            @Override
            public void tick() {
                ArrayList<ToonObject> toons = getToonObjects();
                if(toons != null){
                    scene.TICK.addAll(toons);
                    scene.SCENE.addAllDisplay(toons);
                }
            }

            @Override
            public long getSerialID() {
                return -1;
            }
        });
    }

    @Override
    public long getSerialID() {
        return serial;
    }

    protected void setToonObjects(ArrayList<ToonObject> to) throws NullPointerException {}
    protected abstract ArrayList<ToonObject> getToonObjects();
}
