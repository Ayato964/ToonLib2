package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;

import java.awt.*;

public class Sleep extends TimeConverter {
    public Sleep(long milli){
        super(milli);
    }


    @Override
    protected void clockTick(Graphics2D g, Properties properties, Animation<?> animation, double progress) {}


}
