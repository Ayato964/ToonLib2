package org.ayato.animation;

import org.ayato.animation.image.Gif;
import org.ayato.animation.text.properties.Button;
import org.ayato.animation.text.properties.PropertyAction;

import java.util.function.BooleanSupplier;

public class ImageProperties extends Properties {
    public int w, h;

    public ImageProperties(int x, int y, int w, int h) {
        super(x, y);
        this.w = w;
        this.h = h;
    }

    public ImageProperties button(int bx, int by, int bw, int bh, PropertyAction insert, PropertyAction action){
        properties.add(new Button(bx, by, bw, bh, insert, action));
        return this;
    }
    public ImageProperties button(PropertyAction insert, PropertyAction action){
        properties.add(new Button(x, y, w, h, insert, action));
        return this;
    }
    public ImageProperties ifView(BooleanSupplier how){
        booleanSupplier = how;
        return this;
    }

    @Override
    public ImageProperties getInstance() {
        return this;
    }

    public ImageProperties gif(long l) {
        properties.add(new Gif(l));
        return this;
    }
}
