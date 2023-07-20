package org.ayato.test;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class FactoryTest implements IBaseScene {
    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        AnimationFactory.CONV1.get().drawThisScene();
        AnimationFactory.CONV2.get().drawThisScene();

        Animation.create(scene, "Regret",
                Properties.ofText(100, 20).button(20, 20, ()->Color.WHITE, Color.BLACK, null,
                        property->AnimationFactory.CONV2.get().drawThisScene()), true);




    }
}
