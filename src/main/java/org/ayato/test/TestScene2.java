package org.ayato.test;

import org.ayato.system.AnimationText;
import org.ayato.system.Component;
import org.ayato.system.ExecuteScene;
import org.ayato.system.properties.Properties;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class TestScene2 implements IBaseScene {
    @Override
    public void display(Graphics g) {


    }

    @Override
    public void setup(ExecuteScene scene) {

        AnimationText.create(scene).draw(Component.get(this, "hello"), 10, 20, new Properties().size(64).color(Color.RED));
        AnimationText.create(scene).draw(Component.get(this, "hello"), 50, 60, new Properties()
                .font(new Font("", 0, 32))
                        .color(Color.WHITE)
                        .frame(50, 60, 120, 20, Color.WHITE, Color.GRAY)
                        .talk(this, true, null, "mes1", "mes2")
        );

    }
}
