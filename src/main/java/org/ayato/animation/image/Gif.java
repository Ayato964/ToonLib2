package org.ayato.animation.image;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.animation.text.properties.IProperty;

import java.awt.*;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class Gif implements IProperty {
    private final long loopTime;
    private ImageMaker imageMaker;
    private boolean isFirst = true;
    private BooleanSupplier bool;
    public Gif(long count){
        loopTime = count;
    }
    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
        Animation<ImageMaker> image = (Animation<ImageMaker>) animation;

        if(isFirst){
            imageMaker = image.getViewObject();
            isFirst = false;
            bool = properties.isVisible;
            Thread thread = new Thread(this::gif);
            thread.start();
        }
    }
    public void gif(){
        while (bool.getAsBoolean()){
            try {
                Thread.sleep(loopTime * 1000 / imageMaker.length());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            imageMaker.next();
        }
    }

}
