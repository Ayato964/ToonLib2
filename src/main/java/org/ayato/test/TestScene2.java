package org.ayato.test;

import org.ayato.util.IBaseScene;

import java.awt.*;

public class TestScene2 implements IBaseScene {
    @Override
    public void display(Graphics g) {


        g.setColor(Color.RED);
        g.fillRect(50, 50, 200, 200);
    }

    @Override
    public void setup(Graphics g) {

    }
}
