package org.ayato.test;

import org.ayato.animation.image.AnimationImage;
import org.ayato.animation.image.ImageMaker;
import org.ayato.animation.image.properties.ImageProperties;
import org.ayato.animation.text.properties.TextProperties;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class ImageTest implements IBaseScene {
    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        //view normal image
        AnimationImage.create(scene, new ImageMaker("test", "65535", 200, 200), 0, 0, 100, 50, new ImageProperties());
        // view button image
        AnimationImage.create(scene, new ImageMaker("test", "65535", 500, 500), 0, 60, 100, 50,
                new ImageProperties().button(0, 60, 100, 50, null, property -> {
                    System.out.println("Clicked!!!!!!");
                }));
    }
}
