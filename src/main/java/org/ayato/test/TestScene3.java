package org.ayato.test;

import org.ayato.animation.AnimationKeyButtons;
import org.ayato.animation.AnimationList;
import org.ayato.animation.Properties;
import org.ayato.system.*;
import org.ayato.system.Component;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class TestScene3 implements IBaseScene {
    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        scene.BACKGROUND.mode.setColor(Color.BLACK);
        AnimationList<String, Properties<String>> animationList = new AnimationList<>(scene, Component.get(this, "hello"),
                Properties.ofText().font(new Font("", Font.PLAIN, 32))
                        .color(Color.RED), ()->System.out.println("hello"));

        animationList.add(Component.get(this, "goto.image.test"), () -> scene.changeScene(new ImageTest()));
        animationList.add("LEMON", () -> System.out.println("LEMON"));
        animationList.add("aaa", () -> System.out.println("aaa"));
        animationList.add("bbb", () -> System.out.println("bbb"));

        AnimationKeyButtons<String, AnimationList<String, Properties<String>>> list =
                new AnimationKeyButtons<>(animationList, 10, 50, 100, 50, Color.RED, Color.WHITE);

        list.setVisible(true);

    }
}
