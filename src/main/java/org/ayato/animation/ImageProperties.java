package org.ayato.animation;

import org.ayato.animation.image.Gif;
import org.ayato.animation.image.ImageMaker;
import org.ayato.animation.text.properties.Button;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.LunchScene;
import org.ayato.util.Position;

import java.awt.*;
import java.util.function.BooleanSupplier;

public class ImageProperties extends Properties<ImageMaker> {
    public int w, h;

    public ImageProperties(int x, int y, int w, int h) {
        super(x, y);
        this.w = w;
        this.h = h;
    }

    public ImageProperties button(int bx, int by, int bw, int bh, PropertyAction action){
        Position p = new Position(()->position.x.getAsInt() + bx, ()->position.y.getAsInt() + by, bw, bh);
        properties.add(()->new Button(p,  action, null));
        return this;
    }
    public ImageProperties button(PropertyAction action){
        Position p = new Position(()->position.x.getAsInt(), ()->position.y.getAsInt(), w, h);
        properties.add(()->new Button(p, action, null));
        return this;
    }
    public ImageProperties ifView(BooleanSupplier how){
        isVisible = how;
        return this;
    }

    public ImageProperties gif(long l) {
        properties.add(()->new Gif(l));
        return this;
    }

    @Override
    public void run(LunchScene MASTER, Graphics g, ImageMaker o) {

    }
}
