package org.ayato.test;

import org.ayato.component.Rotate;
import org.ayato.component.Scale;
import org.ayato.component.ToonObject;
import org.ayato.component.Transform;
import org.ayato.util.Position;

import java.awt.*;

public class TestObject extends ToonObject {
    protected TestObject(int x, int y) {
        super(new Transform(
                new Position(x, y, 30, 30),
                new Scale(30, 30),
                new Rotate(60)
        ));
    }

    @Override
    protected void display(Transform transform, Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(transform.getPosition().x(), transform.getPosition().y(), transform.getW(), transform.getH());
        g.fillRect(transform.getPositionAdd(40, 0).x(),
                transform.getPositionAdd(40, 0).y(),
                transform.getW(), transform.getH());
    }

    @Override
    protected void tick(Transform position) {
    }
}
