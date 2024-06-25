package org.ayato.animation;

import org.ayato.animation.image.Gif;
import org.ayato.animation.image.ImageMaker;
import org.ayato.animation.text.properties.Button;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.component.Transform;
import org.ayato.system.ToonMaster;

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
    public void run(Graphics g, ImageMaker o, int cX, int cY) {
        g.drawImage(o.get(), 0, 0, transform.getW(), transform.getH(), null);
    }
}
