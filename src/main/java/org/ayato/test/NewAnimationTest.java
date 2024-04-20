package org.ayato.test;

import org.ayato.animation.Animation;
import org.ayato.animation.AnimationState;
import org.ayato.animation.PropertiesComponent;
import org.ayato.animation.TextProperties;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;
import org.ayato.util.Position;
import org.ayato.util.PropertiesSupplier;

import java.awt.*;
import java.util.function.Supplier;

public class NewAnimationTest implements IBaseScene{
    public static final Supplier<AnimationState> STATE =()-> new AnimationState(
            Color.WHITE,
            Color.BLUE,
            Color.RED,
            new Color(10, 30, 70, 80),
            new Color(10, 30, 70, 80),
            new Color(10, 30, 70, 80)
    );
    public static final PropertiesSupplier<TextProperties> TEMPLATE =(x, y) -> PropertiesComponent.ofText(x, y)
            .color(Color.WHITE)
            .font("", Font.PLAIN, 1.5f);
    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        scene.BACKGROUND.mode.setColor(Color.BLACK);
        Animation<String> animation = scene.createAnimation("YESSS!!", TEMPLATE.of(100, 120).center());

        scene.addAnimation("Hello", TEMPLATE.of(20, 20).center().
                button(0, 0, 40, 20,STATE.get() ,
                        (action)-> System.out.println("cool")));

        scene.addAnimation("Test Animation", TEMPLATE.of(60, 40)
                .button(0, 0, 10, 10, STATE.get(),
                        (a)-> System.out.println("cooler"))
        );
        scene.addAnimation("Test AAAAA", TEMPLATE.of(90, 40)
                .button(0, 0, 10, 10, STATE.get(),
                        (a)-> scene.addAnimation(animation))
        );
    }
}
