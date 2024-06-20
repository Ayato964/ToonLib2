package org.ayato.test;

import org.ayato.system.ToonMaster;
import org.ayato.component.ToonObject;
import org.ayato.util.BaseScene;
import org.ayato.util.Setup;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ToonObjectTest extends BaseScene {
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
    public void setupUIClass(ArrayList<Setup> setups) {
        setups.add(new ButtonSetup(AnimationScene::new, ImageTestScene::new, "<", ">"));
    }

    @Override
    public void createUI(ToonMaster scene) {

    }
}
