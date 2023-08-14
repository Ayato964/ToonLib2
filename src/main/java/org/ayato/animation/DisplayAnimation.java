package org.ayato.animation;

import org.ayato.system.LunchScene;

import java.awt.*;

public interface DisplayAnimation<T> {
    abstract void run(LunchScene MASTER, Properties prop,  Graphics g, T o);
}
