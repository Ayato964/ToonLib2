package org.ayato.test;

import org.ayato.system.AnimationText;
import org.ayato.system.ExecuteScene;
import org.ayato.system.properties.Properties;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class TestScene2 implements IBaseScene {
    @Override
    public void display(Graphics g) {


        g.setColor(Color.RED);
        g.fillRect(50, 50, 200, 200);
    }

    @Override
    public void setup(ExecuteScene scene) {

        AnimationText.create(scene).draw("Hello", 300, 300, new Properties().size(12));
    }
}
