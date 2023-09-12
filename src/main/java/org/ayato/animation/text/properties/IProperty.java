package org.ayato.animation.text.properties;

import org.ayato.animation.AnimationSequentialOrder;
import org.ayato.animation.Properties;
import org.ayato.animation.Animation;

import java.awt.*;

public interface IProperty{

    void runningProperty(Graphics g, Properties properties,  Animation<?> animation);
    void reset(int nx, int ny);

    default void endAnimation(IProperty order){
        order.endTask();
    }
    default void endTask(){}

    default void setup(Graphics g, Properties properties, Animation<?> animation){}
}
