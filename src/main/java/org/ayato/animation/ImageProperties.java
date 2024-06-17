package org.ayato.animation;

import org.ayato.animation.image.Gif;
import org.ayato.animation.image.ImageMaker;
import org.ayato.animation.text.properties.Button;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.LunchScene;
import org.ayato.util.Position;

import java.awt.*;
import java.util.function.BooleanSupplier;

public final class ImageProperties extends Properties<ImageMaker> {

    public ImageProperties(Position position) {
        super(position);
    }

    public ImageProperties button(int bx, int by, int bw, int bh, PropertyAction<Button> action){
        properties.add(()->new Button( new Position(bx, by, bw, bh)
                .setXAddon(()->position.getX()).setYAddon(()->position.getY()), action, null));
        return this;
    }
    public ImageProperties button(PropertyAction<Button> action){
        properties.add(()->new Button(position, action, null));
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
        g.drawImage(o.get(), position.getX(), position.getY(), position.getW(), position.getH(), null);
    }
}
