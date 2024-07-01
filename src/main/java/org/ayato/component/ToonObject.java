package org.ayato.component;

import org.ayato.system.Tick;
import org.ayato.util.Display;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class ToonObject implements Tick, Display {
    public final Transform transform;
    private final long serial = new Random().nextLong(0, 100000);
    private final BufferedImage paint;
    private final int masterW = 1000, masterH = 1000;
    protected ToonObject(Transform transform) {
        this.transform = transform;
        paint = new BufferedImage(masterW, masterH, BufferedImage.TYPE_INT_ARGB);
    }


    @Override
    public final void display(Graphics g) {
        Graphics2D g2 = (Graphics2D) paint.getGraphics(); // child graphic
        objectBackgroundClear(g2);

        g2.rotate(transform.rotate.getRadian());
        display(g2, masterW / 2, masterH / 2, masterW, masterH);
        Vector2D pos = transform.getPosition();
        g.drawImage(paint, pos.x(), pos.y(), transform.getW(), transform.getH(), null);

    }
    private void objectBackgroundClear(Graphics2D g2){
        g2.setColor(new Color(0, 0, 0, 0));
        g2.fillRect(0, 0, masterW, masterH);
    }

    @Override
    public final void tick() {
        tick(transform);
    }

    @Override
    public long getSerialID() {
        return serial;
    }

    protected abstract void display(Graphics2D g, int centerX, int centerY, int masterW, int masterH);
    protected abstract void tick(Transform transform);
}
