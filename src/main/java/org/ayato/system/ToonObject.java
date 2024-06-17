package org.ayato.system;

import org.ayato.util.Display;
import org.ayato.util.Position;

import java.awt.*;
import java.util.Random;

public abstract class ToonObject implements Tick, Display {
    private final Position position;
    private final long serial = new Random().nextLong(0, 100000);
    protected ToonObject(Position position) {
        this.position = position;
    }

    @Override
    public final void display(Graphics g) {
        display(position, g);
    }

    @Override
    public final void tick() {
        tick(position);
    }

    @Override
    public long getSerialID() {
        return serial;
    }

    protected abstract void display(Position position, Graphics g);
    protected abstract void tick(Position position);
}
