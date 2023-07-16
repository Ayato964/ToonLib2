package org.ayato.animation.image.properties;

import org.ayato.AbstractProperties;
import org.ayato.animation.image.AnimationImage;
import org.ayato.animation.text.properties.Button;
import org.ayato.animation.text.properties.PropertyAction;

import java.awt.*;
import java.util.function.Supplier;

public class ImageProperties extends AbstractProperties<AnimationImage> {
    public ImageProperties button(int bx, int by, int bw, int bh, PropertyAction insert, PropertyAction action){
        properties.add(new Button<AnimationImage, ImageProperties>(bx, by, bw, bh, insert, action));
        return this;
    }
}
