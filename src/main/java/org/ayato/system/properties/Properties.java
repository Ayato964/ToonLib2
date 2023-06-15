package org.ayato.system.properties;

import org.ayato.system.AnimationText;

import java.awt.*;
import java.util.ArrayList;

public class Properties {
    private final ArrayList<IProperty> properties;
    private AnimationText animationText;
    public Properties(){
        properties = new ArrayList<>();
    }
    public void addAnimation(AnimationText text){
        animationText = text;
    }

    public void runProp(Graphics g){
        for(IProperty p : properties)
            p.runningProperty(g, this, animationText);
    }

    public Properties size(int size){
        properties.add(((g, properties1, text) -> g.setFont(new Font("", Font.PLAIN, size))));
        return this;
    }
    public Properties font(Font font){
        properties.add(((g, properties1, text) -> g.setFont(font)));
        return this;
    }
}
