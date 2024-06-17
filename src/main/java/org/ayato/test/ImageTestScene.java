package org.ayato.test;

import org.ayato.animation.PropertiesComponent;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.LunchScene;
import org.ayato.system.ToonObject;
import org.ayato.util.IBaseScene;
import org.ayato.util.Setup;

import java.awt.*;
import java.util.ArrayList;

public class ImageTestScene extends IBaseScene {
    @Override
    protected ArrayList<ToonObject> getToonObjects() {
        return null;
    }

    @Override
    public void tick() {

    }

    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        scene.BACKGROUND.mode.setColor(Color.BLACK);
        scene.addAnimation(new ImageMaker("test", "help"), PropertiesComponent.ofImage(20, 20, 50, 50)
                .button(action->{
                    System.out.println("He llo");
                }));
        scene.addAnimation(new ImageMaker("test","loading", 120, 120),
                PropertiesComponent.ofImage(60, 60, 80, 80).gif(2));
    }

    @Override
    public void setupClass(ArrayList<Setup> setups) {
        setups.add(new ButtonSetup(ToonObjectTest::new, NewAnimationTest::new, "<",">"));
    }
}
