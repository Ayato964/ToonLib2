package org.ayato.animation;

import org.ayato.system.ToonMaster;

import java.awt.*;

public interface DisplayAnimation<T> {
    abstract void run(ToonMaster MASTER, Graphics g, T o);
}
