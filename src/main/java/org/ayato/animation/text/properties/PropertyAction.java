package org.ayato.animation.text.properties;


public interface PropertyAction<T extends IProperty> {
    void action(T property);
}
