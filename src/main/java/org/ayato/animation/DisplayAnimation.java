package org.ayato.animation;

import org.ayato.system.LunchScene;

import java.awt.*;

public interface DisplayAnimation<T> {
    abstract void run(LunchScene MASTER, Graphics g, T o);
}
