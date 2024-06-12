package org.ayato.test;

import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;
import org.ayato.util.Setup;

import java.awt.*;
import java.util.ArrayList;

public class ModuleAnimationTest implements IBaseScene {

    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        scene.BACKGROUND.mode.setColor(Color.BLACK);
        scene.HANDLER.addInputModule("Input Here!!", s ->{
            scene.addAnimation(s, NewAnimationTest.TEMPLATE.of(0, 100).center());
        }, NewAnimationTest.TEMPLATE.of(10, 10));
    }

    @Override
    public void setupClass(ArrayList<Setup> setups) {
        setups.add(new ButtonSetup(NewAnimationTest::new, null, "<", ">"));
    }
}
