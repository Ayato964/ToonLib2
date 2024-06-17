package org.ayato.test;

import org.ayato.system.LunchScene;
import org.ayato.system.ToonObject;
import org.ayato.util.Position;

import java.awt.*;

public class TestObject extends ToonObject {
    protected TestObject(int x, int y) {
        super(new Position(x, y, 100, 100));
    }

    @Override
    protected void display(Position position, Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(position.getX(), position.getY(), position.getW(), position.getH());
    }

    @Override
    protected void tick(Position position) {
    }
}
