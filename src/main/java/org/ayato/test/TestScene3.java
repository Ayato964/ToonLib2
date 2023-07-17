package org.ayato.test;

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
                Properties.ofText(0, 0).font(new Font("", Font.PLAIN, 32))
                        .color(Color.RED), property -> System.out.println("hello"));

        animationList.add(Component.get(this, "goto.image.test"), property -> scene.changeScene(new ImageTest()));
        animationList.add("LEMON", property -> System.out.println("LEMON"));
        animationList.add("aaa", property -> System.out.println("aaa"));
        animationList.add("bbb", property -> System.out.println("bbb"));

        //AnimationMouseButtonList<AnimationList<?>> list = AnimationMouseButtonList.generate(animationList, 50, 50, 120, 30);
      //  AnimationKeyButtonList<AnimationList<String>> list = AnimationKeyButtonList.generate(animationList, 50, 50, 60, 30);
       // list.setVisible(true);
        //list.setVisible(false);

    }
}
