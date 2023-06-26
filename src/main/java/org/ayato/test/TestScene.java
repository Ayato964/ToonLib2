package org.ayato.test;

import org.ayato.main.TestMain;
import org.ayato.system.AnimationText;
import org.ayato.system.Component;
import org.ayato.system.ExecuteScene;
import org.ayato.system.properties.Properties;
import org.ayato.util.IBaseScene;

import java.awt.*;
import java.awt.event.MouseListener;

public class TestScene implements IBaseScene {
    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(ExecuteScene scene) {

        AnimationText.create(scene, Component.get(this, "hello"), 10, 30,
                new Properties().size(64)
                        .frame(10, 30, 100, 20, new Color(0xFFFFFF), new Color(0, 10, 0, 50))
                        .color(Color.BLUE));

        AnimationText.create(scene,"Test Buttoon", 60, 30, new Properties().font(new Font("", Font.PLAIN, 32))

                .button(60, 30, 120, 10, Color.BLACK, Color.WHITE, null,property->{
                    TestMain.MASTER1.changeScene(new TestScene2());
                    TestMain.MASTER1.FRAME.removeMouseListener((MouseListener) property);
                }));
        AnimationText.create(scene, "Press the message", 60, 60, new Properties().font(new Font("", Font.PLAIN, 64))
                        .frame(60, 60, 100, 20, Color.WHITE, new Color(192, 192, 192, 50))
                .color(Color.RED)
                .input(60, 60, 100, 20, System.out::println)
        );

    }
}
