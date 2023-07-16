package org.ayato.animation;

import org.ayato.animation.properties.TextProperties;
import org.ayato.system.LunchScene;

import java.awt.*;

public class AnimationImage extends AbstractAnimation<Image> {
    int w, h;

    protected AnimationImage(LunchScene master) {
        super(master);
    }
    public static AnimationImage create(LunchScene scene, Image image, int x, int y, int w, int h, TextProperties properties){
        AnimationImage i = new AnimationImage(scene);
        i.mes = image;
        i.x = x;
        i.y = y;
        i.w = w;
        i.h = h;
        if(properties != null) {
            i.properties = properties;
        }
        return i;
    }

    @Override
    public void display(Graphics g) {

    }
}
