package org.ayato.test;

import org.ayato.animation.AnimationKeyButtons;
import org.ayato.animation.AnimationList;
import org.ayato.animation.Properties;
import org.ayato.system.Background;
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
        AnimationList<String, Properties<String>> alist =
                new AnimationList<>(scene, Component.get(this, "factory"),
                        Properties.ofText().font(new Font("", Font.PLAIN, 32)),
                        ()-> scene.changeScene(new FactoryTest())
                        );

        alist.add(Component.get(this, "2"), ()->scene.changeScene(new TS2()));

        AnimationKeyButtons<String, AnimationList<String, Properties<String>>> list =
                new AnimationKeyButtons<>(alist, 20, 20, 100, 50, Color.RED, Color.WHITE);
        list.setVisible(true);

    }
}
