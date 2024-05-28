package org.ayato.animation.text.properties;

import java.awt.event.KeyEvent;

public interface ChangeFunction<T> {
    T getCondition(boolean isClicked, KeyEvent event, int count);
}
