package org.ayato.animation;

import org.ayato.animation.image.ImageMaker;
import org.ayato.system.LunchScene;
import org.ayato.util.VoidSupplier;

import java.awt.*;

public class AnimationComponent {
    public static AObject<String> ofText(String str){

        return new AObject<>(str) {
            @Override
            public void run(LunchScene MASTER, Properties prop, Graphics g, String o) {
                g.drawString(o, prop.x * MASTER.FRAME.DW, prop.y * MASTER.FRAME.DH);
            }
        };
    }
    public static AObject<ImageMaker> ofImage(ImageMaker image){
        return new AObject<>(image) {
            @Override
            public void run(LunchScene MASTER, Properties p, Graphics g, ImageMaker o) {
                ImageProperties prop = (ImageProperties) p;
                g.drawImage(o.getEditImage(),
                        prop.x * MASTER.FRAME.DW, prop.y * MASTER.FRAME.DH
                        , prop.w * MASTER.FRAME.DW, prop.h * MASTER.FRAME.DH, null);
            }
        };
    }

    public static AObject<VoidSupplier> ofRect(){
        return  new AObject<>(null) {
            @Override
            public void run(LunchScene MASTER, Properties p, Graphics g, VoidSupplier o) {
                RectProperties prop = (RectProperties) p;
                g.drawRect(prop.x, prop.y, prop.w, prop.h);
            }
        };
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
        return new AObject<>(t) {
            @Override
            public void run(LunchScene MASTER, Properties prop, Graphics g, T o) {
                run.run(MASTER, prop, g, o);
            }
        };
    }
}
