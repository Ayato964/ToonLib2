package org.ayato.animation.text.properties;

import java.awt.*;

public interface PropertyAction<T extends IProperty> {
    void action(T property);
}
