package org.ayato.animation;

import org.ayato.animation.image.ImageMaker;
import org.ayato.system.LunchScene;
import org.ayato.util.VoidSupplier;

import java.awt.*;

public class AnimationComponent {
    public static AObject<String> ofText(String str){
        return null;
    }
    public static AObject<ImageMaker> ofImage(ImageMaker image){
        return null;
    }

    public static AObject<VoidSupplier> ofRect(){
        return null;
    }
    /*

    public static AObject<VoidSupplier> ofLine(){
        return new AObject<>(null) {
            @Override
            public void run(LunchScene MASTER, Properties prop, Graphics g, VoidSupplier o) {
                g.drawLine(prop.x, prop.y, prop.x2, prop.y2);
            }
        };
    }

     */



    public static  <T> AObject<T> ofCustom(T t, DisplayAnimation<T> run){
        return null;
    }
}
