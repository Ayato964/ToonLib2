package org.ayato.util;

import java.awt.event.KeyEvent;

public interface IListenerDecoder {
    void overlap();
    void press();
    void unOverlap();
    default void KeyTyped(KeyEvent event){}
}
