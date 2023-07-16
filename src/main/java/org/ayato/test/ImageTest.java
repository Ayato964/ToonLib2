package org.ayato.test;

import org.ayato.animation.image.AnimationImage;
import org.ayato.animation.image.ImageMaker;
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
        AnimationImage.create(scene, new ImageMaker("textures/test", "65535"), 20, 20, 100, 50, new TextProperties());
    }
}
