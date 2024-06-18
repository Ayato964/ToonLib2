package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.AnimationState;
import org.ayato.animation.Properties;
import org.ayato.component.Transform;
import org.ayato.component.Vector2D;

import java.awt.*;

public class Frame implements IProperty {
    Transform transform;
    AnimationState colorState;

    public Frame(Transform p, AnimationState colorState) {
        transform = p;
        this.colorState = colorState;

    }

    @Override
    public void runningProperty(Graphics2D og, Graphics g, Properties properties, Animation<?> text){
        g.setColor(colorState.getState(AnimationState.BACKGROUND));
        Vector2D vec = transform.getPosition();
        g.fillRect(vec.x() , vec.y(), transform.getW(), transform.getH());

        g.setColor(colorState.getState(AnimationState.FRAME));

        g.drawRect(vec.x(), vec.y(),
                transform.getW() , transform.getH());
    }


}
