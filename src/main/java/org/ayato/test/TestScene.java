package org.ayato.test;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.main.TestMain;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;
import java.awt.event.MouseListener;

public class TestScene implements IBaseScene {
    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        Animation.create(scene, Component.get(this, "hello"),
                Properties.ofText(5, 30).size(64)
                        .center()
                        .centerFrame(  100, 20, ()->new Color(0xFFFFFF), new Color(0, 10, 0, 50))
                        .color(Color.BLUE));

        Animation.create(scene,"Test Buttoon", Properties.ofText(60, 30).font(new Font("", Font.PLAIN, 32))
                        .center()
                .button(120, 10, ()->Color.BLACK, Color.WHITE, null,property->{
                    TestMain.MASTER1.changeScene(new TestScene2());
                    TestMain.MASTER1.FRAME.removeMouseListener((MouseListener) property);
                }));


        Animation.create(scene, "Press the message", Properties.ofText(60, 60).font(new Font("", Font.PLAIN, 64))
                        .frame(100, 20, ()->Color.WHITE, new Color(192, 192, 192, 50))
                .color(Color.RED)
                .input(60, 60, 100, 20, System.out::println)
        );

    }
}
