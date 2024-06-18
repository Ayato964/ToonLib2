package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.util.LastRunningProperty;

import java.awt.*;

public class ChangeColor implements  IProperty, LastRunningProperty {
    private final Color c;

    public ChangeColor(Color change){
        c = change;
    }

    @Override
    public void runningProperty(Graphics2D og, Graphics g, Properties properties, Animation<?> animation) {
        og.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), og.getColor().getAlpha()));
    }


}
