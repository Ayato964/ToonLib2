package org.ayato.test;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.animation.PropertiesComponent;
import org.ayato.animation.TextProperties;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;
import org.ayato.util.PropertiesSupplier;

import java.awt.*;
import java.util.function.Supplier;

public class NewAnimationTest implements IBaseScene{
    public static final PropertiesSupplier<TextProperties> TEMPLATE =(x, y) -> PropertiesComponent.ofText(x, y)
            .color(Color.RED)
            .font(new Font("", Font.PLAIN, 32));
    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        scene.BACKGROUND.mode.setColor(Color.BLACK);
        scene.animation("Hello", TEMPLATE.of(20, 20).
                button(20, 18, 10, 10, ()->Color.RED, Color.RED, null,
                        (action)-> System.out.println("cool")));
    }
}
