package org.ayato.system;

import org.ayato.util.Display;
import org.ayato.util.Position;

import java.awt.*;

public abstract class ToonObject implements Tick, Display {
    private final Position position;
    private final LunchScene master;
    protected ToonObject(Position position, LunchScene master) {
        this.position = position;
        this.master = master;
    }

    @Override
    public final void display(Graphics g) {
        display(position, g);
    }

    @Override
    public final void tick() {
        tick(position);
    }

    protected abstract void display(Position position, Graphics g);
    protected abstract void tick(Position position);
}
