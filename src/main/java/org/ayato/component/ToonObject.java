package org.ayato.component;

import org.ayato.system.LunchScene;
import org.ayato.system.MyFrame;
import org.ayato.system.Tick;
import org.ayato.util.Display;
import org.ayato.util.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class ToonObject implements Tick, Display {
    private final Transform transform;
    private final long serial = new Random().nextLong(0, 100000);
    protected ToonObject(Transform transform) {
        this.transform = transform;
        MyFrame f = LunchScene.getINSTANCE().FRAME;
    }

    @Override
    public final void display(Graphics g) {

        display(transform, g);
    }

    @Override
    public final void tick() {
        tick(transform);
    }

    @Override
    public long getSerialID() {
        return serial;
    }

    protected abstract void display(Transform transform, Graphics g);
    protected abstract void tick(Transform transform);
}
