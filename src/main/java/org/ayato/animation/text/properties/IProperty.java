package org.ayato.animation.text.properties;

import org.ayato.animation.Properties;
import org.ayato.animation.Animation;

import java.awt.*;

public interface IProperty<T>{

    void runningProperty(Graphics g, Properties<T> properties,  Animation<T> animation);
}
