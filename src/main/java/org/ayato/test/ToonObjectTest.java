package org.ayato.test;

import org.ayato.system.LunchScene;
import org.ayato.system.ToonObject;
import org.ayato.util.IBaseScene;
import org.ayato.util.Setup;

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
        if(count >= 3000){
            toonObjects.add(new TestObject(new Random().nextInt(0, 400), new Random().nextInt(0, 200)));
            count = 0;
        }
    }

    @Override
    public void setToonObjects( ArrayList<ToonObject> to) throws NullPointerException {
        to.add(new TestObject(100, 100));
    }

    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setupClass(ArrayList<Setup> setups) {
        setups.add(new ButtonSetup(AnimationScene::new, ImageTestScene::new, "<", ">"));
    }

    @Override
    public void setup(LunchScene scene) {

    }
}
