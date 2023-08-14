package org.ayato.test;

import org.ayato.animation.*;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class TS1 implements IBaseScene {

    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        scene.BACKGROUND.mode.setColor(Color.BLACK);
        AnimationList<String, Properties> alist =
                new AnimationList<>(scene,
                        PropertiesComponent.ofText().font(new Font("", Font.PLAIN, 32)));

        alist.add(AnimationComponent.ofText(Component.get(this, "factory")), ()-> scene.changeScene(new FactoryTest()));
        alist.add(AnimationComponent.ofText(Component.get(this, "2")), ()->scene.changeScene(new TS2()));

        AnimationKeyButtons<String, AnimationList<String, Properties>> list =
                new AnimationKeyButtons<>(alist, 20, 20, 100, 50, Color.RED, Color.WHITE, new Color(127, 127, 127, 80));
        list.setVisible(true);

    }
}
