package org.ayato.animation.image;

import org.ayato.animation.AbstractAnimation;
import org.ayato.animation.image.properties.ImageProperties;
import org.ayato.system.LunchScene;

import java.awt.*;
import java.util.function.BooleanSupplier;

public class AnimationImage extends AbstractAnimation<Image> {
    int w, h;

    protected AnimationImage(LunchScene master, BooleanSupplier supplier) {
        super(master, supplier);
    }
    public static AnimationImage create(LunchScene scene, ImageMaker image, int x, int y, int w, int h, ImageProperties properties){
        AnimationImage i = new AnimationImage(scene, ()->true);
        i.mes = image.getEditImage();
        i.x = x;
        i.y = y;
        i.w = w;
        i.h = h;
        if(properties != null) {
            i.properties = properties;
            properties.addAnimation(i);
        }
        scene.SCENE.addDisplay(i);
        return i;
    }
    @Override
    protected void run(Graphics g, Image o) {
            g.drawImage(o, x * MASTER.FRAME.DW, y * MASTER.FRAME.DH, w * MASTER.FRAME.DW, h * MASTER.FRAME.DH, null);
    }
}
