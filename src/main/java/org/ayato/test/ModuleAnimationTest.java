package org.ayato.test;

import org.ayato.system.ToonMaster;
import org.ayato.util.BaseScene;
import org.ayato.util.Setup;

import java.awt.*;
import java.util.ArrayList;

public class ModuleAnimationTest extends BaseScene {

    @Override
    public void display(Graphics g) {

    }

    @Override
    public void createUI(ToonMaster scene) {
        scene.BACKGROUND.mode.setColor(Color.BLACK);
        scene.HANDLER.addInputModule("Input Here!!", s ->{
            scene.addAnimation(s, NewAnimationTest.TEMPLATE.of(0, 100).center());
        }, 70, 20, NewAnimationTest.TEMPLATE.of(10, 10));
    }

    @Override
    public void setupUIClass(ArrayList<Setup> setups) {
        setups.add(new ButtonSetup(NewAnimationTest::new, AnimationScene::new, "<", ">"));
    }

    @Override
    public void tick() {

    }


}
