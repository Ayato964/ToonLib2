package org.ayato.test;

import org.ayato.animation.*;
import org.ayato.animation.image.ImageMaker;
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
        scene.BACKGROUND.mode = Background.BackgroundMode.COLOR;
        scene.BACKGROUND.mode.setColor(Color.BLACK);
        AnimationList<String, Properties> alist =
                new AnimationList<>(scene,
                        PropertiesComponent.ofText().font(new Font("", Font.PLAIN, 32)));

        alist.add(AnimationComponent.ofText(Component.get(this, "factory")), key-> scene.changeScene(new FactoryTest()));
        alist.add(AnimationComponent.ofText(Component.get(this, "2")), key->scene.changeScene(new TS2()));

        AnimationKeyButtons<String, AnimationList<String, Properties>> list =
                new AnimationKeyButtons<>(alist, 20, 20, 100, 50, Color.RED, Color.WHITE, new Color(127, 127, 127, 80));
        list.setVisible(true);

        Animation.create(scene, AnimationComponent.ofText(Component.getForGlobal("test")),
                PropertiesComponent
                        .ofText(0, 10)
                        .color(Color.RED)
                        .font(new Font("", Font.PLAIN, 32)), true);
    }
}
