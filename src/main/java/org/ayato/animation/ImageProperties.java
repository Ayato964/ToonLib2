package org.ayato.animation;

import org.ayato.animation.image.Gif;
import org.ayato.animation.image.ImageMaker;
import org.ayato.animation.text.properties.Button;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.component.Transform;
import org.ayato.component.Vector2D;
import org.ayato.system.LunchScene;
import org.ayato.component.Position;

import java.awt.*;
import java.util.function.BooleanSupplier;

public final class ImageProperties extends Properties<ImageMaker> {

    public ImageProperties(Transform transform) {
        super(transform);
    }

    public ImageProperties button(int bx, int by, int bw, int bh, PropertyAction<Button> action){
        Transform trans = new Transform(bx, by, bw, bh);
        trans.position.addAddon(transform.position);

        properties.add(()->new Button( trans, action, null));

        return this;
    }
    public ImageProperties button(PropertyAction<Button> action){
        properties.add(()->new Button(transform, action, null));
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
        Vector2D vec = transform.getPosition();
        g.drawImage(o.get(), vec.x(), vec.y(), transform.getW(), transform.getH(), null);
    }
}
