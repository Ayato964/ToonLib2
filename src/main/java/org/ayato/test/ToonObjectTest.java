package org.ayato.test;

import org.ayato.system.LunchScene;
import org.ayato.system.ToonObject;
import org.ayato.util.IBaseScene;
import org.ayato.util.Setup;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ToonObjectTest extends IBaseScene {
    private int count = 0;
    @Override
    public void tick() {
        count ++;
        if(count >= 3000){
            addObject(new TestObject(new Random().nextInt(0, 400), new Random().nextInt(0, 200)));
            count = 0;
        }
    }

    @Override
    public void setToonObjects( CopyOnWriteArrayList<ToonObject> to) {
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
