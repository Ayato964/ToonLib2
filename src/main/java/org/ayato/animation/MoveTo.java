package org.ayato.animation;

import org.ayato.util.Position;

import java.awt.*;

public class MoveTo extends TimeConverter{
    private final int x, y;
    private int dx, dy, currentX, currentY;
    private final VelocityFormat format;
    public MoveTo(int x, int y, long maxTime, VelocityFormat format1) {
        super(maxTime);
        this.x = x;
        this.y = y;
        this.format = format1;
    }

    @Override
    public void setupProperty(Graphics g, Properties<?> properties, Animation<?> animation) {
        super.setupProperty(g, properties, animation);
        dx = properties.position.getNormalX();
        dy = properties.position.getNormalY();
        currentX = Math.abs(x - dx);
        currentY = Math.abs(y - dy);
    }

    @Override
    protected void clockTick(Graphics g, Properties properties, Animation<?> animation, double progress) {
        Position position = properties.position;
        switch (format) {
            case CURVE-> {
                position.setX((int) (position.getNormalX() + (x - position.getNormalX()) * progress));
                position.setY((int) (position.getNormalY() + (y - position.getNormalY()) * progress));
            }
            case CONSTANT -> {
                position.setX((int) (dx + currentX * progress));
                position.setY((int) (dy + currentY * progress));
            }
        }

    }
    public enum VelocityFormat{
        CONSTANT,
        CURVE
    }
}