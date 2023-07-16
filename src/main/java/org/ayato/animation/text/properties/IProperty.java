package org.ayato.animation.text.properties;

import org.ayato.AbstractProperties;
import org.ayato.animation.AbstractAnimation;

import java.awt.*;

public interface IProperty<T extends AbstractAnimation<?>, M extends AbstractProperties<T>> {

    public void runningProperty(Graphics g, M properties, T text);
}
