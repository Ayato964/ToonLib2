package org.ayato.test;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.LunchScene;
import org.ayato.util.Event;
import org.ayato.util.IBaseScene;

import java.awt.*;
import java.util.Objects;

public class ImageTest implements IBaseScene {
    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        Event.create(this.getClass(), "event1", null);
        //view normal image
        Animation.create(scene, new ImageMaker("test", "65535", 200, 200),  Properties.ofImage(0, 0, 100, 100));
        /*
        AnimationImage.create(scene, new ImageMaker("test", "65535", 500, 500), 0, 60, 100, 50,
                new ImageProperties().button(0, 60, 100, 50, null, property -> Objects.requireNonNull(Event.get(this.getClass(), "event1")).clear()));

        AnimationImage.create(scene, new ImageMaker("test", "65535", 700, 500), 110, 60, 100, 50,
                new ImageProperties()
                        .ifView(()-> Objects.requireNonNull(Event.get(this.getClass(), "event1")).getEvent())
        );

         */
    }
}
