package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;

import java.awt.*;

public class ChangeColor implements  IProperty {
    private final Color c;

    public ChangeColor(Color change){
        c = change;
    }

    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
        g.setColor(c);
    }


}
