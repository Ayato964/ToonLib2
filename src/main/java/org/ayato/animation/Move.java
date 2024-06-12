package org.ayato.animation;

import java.awt.*;

public class Move extends TimeConverter{
    public Move(long maxTime, int x, int y) {
        super(maxTime, -1);
    }

    @Override
    public void setupProperty(Graphics g, Properties<?> properties, Animation<?> animation) {
        super.setupProperty(g, properties, animation);
    }

    @Override
    protected void clockTick(Graphics g, Properties properties, Animation<?> animation, int secTime) {

    }
}
