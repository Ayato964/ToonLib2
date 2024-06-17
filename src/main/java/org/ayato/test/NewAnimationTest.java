package org.ayato.test;

import org.ayato.animation.*;
import org.ayato.animation.text.properties.CheckBox;
import org.ayato.system.LunchScene;
import org.ayato.system.ToonObject;
import org.ayato.util.Display;
import org.ayato.util.IBaseScene;
import org.ayato.util.PropertiesSupplier;
import org.ayato.util.Setup;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

public class NewAnimationTest extends IBaseScene{
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

    private int count = 0;
    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        scene.BACKGROUND.mode.setColor(Color.BLACK);

    }

    @Override
    public void setupClass(ArrayList<Setup> setups) {
        setups.add(new NormalAnimation());
        setups.add(new AnimationSetup());
        setups.add(new ButtonSetup(null, ModuleAnimationTest::new, "<", ">"));
    }

    @Override
    public void tick() {
        count ++;
    }

    private class NormalAnimation implements Setup{

        @Override
        public void setup(LunchScene scene) {
            Animation<String> button = scene.createAnimation("Selected!!(Center)", TEMPLATE.of(0, 50).center());
            scene.addAnimation("Normal", TEMPLATE.of(10, 10));
            scene.addAnimation("Button" , TEMPLATE.of(100, 10)
                    .button(0, 0, 30, 15, STATE.get(), a->scene.addAnimation(button)));
            scene.addAnimation((Supplier<String>) () -> String.valueOf(NewAnimationTest.this.count), TEMPLATE.of(150, 10).displayInOrder(200));
            scene.addAnimation("ChooseBox1", TEMPLATE.of(200, 10)
                    .chooseBox(aBoolean -> System.out.println("1"), STATE.get(), Color.WHITE, CheckBox.Duration.LEFT)).setGroup("g1");
            scene.addAnimation("ChooseBox2", TEMPLATE.of(260, 10)
                    .chooseBox(aBoolean -> System.out.println("2"), STATE.get(), Color.WHITE, CheckBox.Duration.LEFT)).setGroup("g1");
            scene.addAnimation("CheckBox", TEMPLATE.of(320, 10)
                    .checkBox(aBoolean -> System.out.println("Check!"), STATE.get(), Color.WHITE, CheckBox.Duration.LEFT));
        }
    }

    private static final class AnimationSetup implements Setup{
        @Override
        public void setup(LunchScene scene) {
            scene.addAnimation("FadeIn", TEMPLATE.of(10, 100).fadeIn(300));
            scene.addAnimation("FadeOut", TEMPLATE.of(50, 100).fadeOut(300));

            scene.addAnimation("Matrix", TEMPLATE.of(100, 100).pushMatrix()
                    .fadeIn(100)
                    .fadeOut(100)
                    .endMatrix());
            scene.addAnimation("DisplayInorder", TEMPLATE.of(150, 100)
                    .displayInOrder(200));


        }
    }
}
