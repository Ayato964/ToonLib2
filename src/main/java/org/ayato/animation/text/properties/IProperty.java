package org.ayato.animation.text.properties;

import org.ayato.animation.Properties;
import org.ayato.animation.Animation;

import java.awt.*;

public interface IProperty{

    void runningProperty(Graphics2D og, Graphics g, Properties properties,  Animation<?> animation);

    default void setupProperty(Graphics2D o2,Graphics g, Properties<?> properties, Animation<?> animation){}

    default boolean isEnd(){
        return true;
    }
}
