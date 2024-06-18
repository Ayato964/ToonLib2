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
                new Position(x, y, 100, 100),
                new Scale(100, 100),
                new Rotate(0)
        ));
    }

    @Override
    protected void display(Graphics2D g, int centerX, int centerY) {
        g.setColor(Color.WHITE);

        g.fillRect(centerX - 100, centerY - 100, 200, 200);
        g.fillRect(centerX + 100, centerY, 200, 200);
    }

    @Override
    protected void tick(Transform position) {
    }
}
