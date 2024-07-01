package org.ayato.test;

import org.ayato.animation.PropertiesComponent;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.ToonMaster;
import org.ayato.util.BaseScene;
import org.ayato.util.Setup;

import java.awt.*;
import java.util.ArrayList;

public class ImageTestScene extends BaseScene {

    @Override
    public void tick() {

    }

    @Override
    public void display(Graphics g) {

    }

    @Override
    public void createUI(ToonMaster scene) {
        scene.addAnimation(new ImageMaker("test", "help"), PropertiesComponent.ofImage(20, 20, 50, 50)
                .button(action->{
                    System.out.println("He llo");
                }));
        scene.addAnimation(new ImageMaker("test","loading", 120, 120),
                PropertiesComponent.ofImage(60, 60, 80, 80).gif(2));
    }

    @Override
    public void setupUIClass(ArrayList<Setup> setups) {
        setups.add(new ButtonSetup(ToonObjectTest::new, NewAnimationTest::new, "<",">"));
    }
}
