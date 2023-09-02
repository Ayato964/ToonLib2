package org.ayato.test;

import org.ayato.animation.Animation;
import org.ayato.animation.AnimationComponent;
import org.ayato.animation.PropertiesComponent;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.Background;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class TS2 implements IBaseScene {
    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        scene.BACKGROUND.mode = Background.BackgroundMode.IMAGE;
        scene.BACKGROUND.mode.setImage(new ImageMaker("test", "65535"));

        Animation.create(scene, AnimationComponent.ofText("Hello!!!!"),
                PropertiesComponent.ofText(20, 20)
                        .color(Color.BLACK)
                        .font(new Font("", Font.PLAIN, 32))
                        .displayInOrder(10), true);
        Animation.create(scene, AnimationComponent.ofImage(new ImageMaker("test", "loading", 120,120)),
                PropertiesComponent.ofImage(50, 50, 50, 50)
                        .gif(10)
                , true);
    }
}
