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
    public void runningProperty(Graphics2D og, Graphics g, Properties properties, Animation<?> animation) {
        properties.transform.position.setX(x);
        properties.transform.position.setY(y);
        isEnd = true;
    }

    @Override
    public boolean isEnd() {
        return isEnd;
    }
}
