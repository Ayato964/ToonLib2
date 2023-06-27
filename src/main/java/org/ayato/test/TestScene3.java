package org.ayato.test;

import org.ayato.system.*;
import org.ayato.system.Component;
import org.ayato.system.properties.Properties;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class TestScene3 implements IBaseScene {
    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(ExecuteScene scene) {
        AnimationList<String> animationList = AnimationList.create(scene, Component.get(this, "hello"),
                new Properties().font(new Font("", Font.PLAIN, 32))
                        .color(Color.BLACK), AnimationText::create, property -> System.out.println("hello"));

        animationList.add("APPLE", property -> System.out.println("APPLE"));
        animationList.add("LEMON", property -> System.out.println("LEMON"));
        animationList.add("aaa", property -> System.out.println("aaa"));
        animationList.add("bbb", property -> System.out.println("bbb"));

        //AnimationMouseButtonList<AnimationList<?>> list = AnimationMouseButtonList.generate(animationList, 50, 50, 120, 30);
        AnimationKeyButtonList<AnimationList<String>> list = AnimationKeyButtonList.generate(animationList, 50, 50, 60, 30);
        list.setVisible(true);
        list.setVisible(false);

    }
}
