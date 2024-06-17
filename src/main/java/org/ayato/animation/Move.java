package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;

import java.awt.*;

public class Move implements IProperty {
    private final int x, y;
    private boolean isEnd = false;
    public Move(int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }

    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
        properties.position.setX(x);
        properties.position.setY(y);
        isEnd = true;
    }

    @Override
    public boolean isEnd() {
        return isEnd;
    }
}
