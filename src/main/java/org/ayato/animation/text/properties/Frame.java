package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.AnimationState;
import org.ayato.animation.Properties;
import org.ayato.util.Position;

import java.awt.*;

public class Frame implements IProperty {
    Position position;
    AnimationState colorState;

    public Frame(Position p, AnimationState colorState) {
        position = p;
        this.colorState = colorState;

    }

    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> text){
        g.setColor(colorState.getState(AnimationState.BACKGROUND));
        g.fillRect(position.getX() , position.getY(),
                position.getW(), position.getH());
        g.setColor(colorState.getState(AnimationState.FRAME));
        g.drawRect(position.getX() , position.getY(),
                position.getW() , position.getH());
    }


}
