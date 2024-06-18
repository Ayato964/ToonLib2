package org.ayato.animation.text.properties;

import org.ayato.animation.AnimationState;
import org.ayato.animation.Properties;
import org.ayato.animation.Animation;
import org.ayato.component.Transform;
import org.ayato.system.LunchScene;
import org.ayato.util.IListenerDecoder;
import org.ayato.util.KeyInputs;
import org.ayato.util.MouseInputs;

import java.awt.*;

public class Button implements IProperty, IListenerDecoder {
    Transform transform;
    PropertyAction<Button> action;
    final Frame frame;
    private boolean isFirst = true;

    public Button(Transform transform, PropertyAction<Button> action, Frame frame) {
        this.transform = transform;
        this.action = action;
        this.frame = frame;
    }

    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> text) {
        if(isFirst){
            isFirst = false;
            //position.setYAddon(()->g.getFontMetrics().getHeight());
            KeyInputs.add(this);
            MouseInputs.add(transform, this);
        }
        if(frame != null)
            frame.runningProperty(g, properties, text);
    }



    @Override
    public void overlap() {
        if(frame != null)
            frame.colorState.setState(AnimationState.OVERLAP);
        LunchScene.getINSTANCE().FRAME.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void press() {
        action.action(this);
    }

    @Override
    public void unOverlap() {
        if(frame != null)
            frame.colorState.setState(AnimationState.DEFAULT);
        LunchScene.getINSTANCE().FRAME.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
