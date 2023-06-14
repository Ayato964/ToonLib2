package org.ayato.test;

import org.ayato.main.TestMain;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class TestScene implements IBaseScene {
    int i = 0;
    @Override
    public void display(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 200, 200);
        i ++;
        if(i > 500){
            TestMain.MASTER1.changeScene(new TestScene2());
        }
    }

    @Override
    public void setup(Graphics g) {

    }
}
