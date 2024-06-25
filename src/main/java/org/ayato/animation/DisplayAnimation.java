package org.ayato.animation;

import org.ayato.system.ToonMaster;

import java.awt.*;

public interface DisplayAnimation<T> {
    abstract void run(Graphics g, T o, int centerX, int centerY);
}
