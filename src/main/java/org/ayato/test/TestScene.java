package org.ayato.test;

import org.ayato.main.TestMain;
import org.ayato.system.AnimationText;
import org.ayato.system.Component;
import org.ayato.system.ExecuteScene;
import org.ayato.system.properties.Properties;
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
    public void setup(ExecuteScene scene) {
        AnimationText.create(scene).draw(Component.get(this, "hello"), 400, 50, new Properties().size(64));
    }
}
