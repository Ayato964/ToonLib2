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
        properties.add(()->new Button( new Position(bx, by, bw, bh)
                .setXAddon(()->position.getX()).setYAddon(()->position.getY()), action, null));
        return this;
    }
    public ImageProperties button(PropertyAction action){
        Position p = new Position(0, 0, w, h).setXAddon(()->position.getX()).setYAddon(()->position.getY());
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
