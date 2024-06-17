package org.ayato.test;

import org.ayato.system.LunchScene;
import org.ayato.system.ToonObject;
import org.ayato.util.IBaseScene;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ToonObjectTest extends IBaseScene {
    private int count = 0;
    private final ArrayList<ToonObject> toonObjects = new ArrayList<>();

    @Override
    public ArrayList<ToonObject> getToonObjects() {
        return toonObjects;
    }

    @Override
    public void tick() {
        count ++;
        System.out.println(count);
        if(count >= 3000){
            toonObjects.add(new TestObject(new Random().nextInt(0, 1280), new Random().nextInt(0, 900)));
            count = 0;
        }
    }

    @Override
    public void setToonObjects(ArrayList<ToonObject> to) throws NullPointerException {
        to.add(new TestObject(100, 100));
    }

    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {

    }
}
