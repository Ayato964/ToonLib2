package org.ayato.animation.text.properties;

import org.ayato.animation.AnimationState;
import org.ayato.animation.Properties;
import org.ayato.animation.Animation;
import org.ayato.util.IListenerDecoder;
import org.ayato.util.KeyInputs;
import org.ayato.util.MouseInputs;
import org.ayato.util.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button implements IProperty, IListenerDecoder {
    Position position;
    PropertyAction action;
    final Frame frame;
    private boolean isFirst = true;

    public Button(Position position, PropertyAction action, Frame frame) {
        this.position = position;
        this.action = action;
        this.frame = frame;
    }

    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> text) {
        if(isFirst){
            isFirst = false;
            //position.setYAddon(()->g.getFontMetrics().getHeight());
            KeyInputs.add(this);
            MouseInputs.add(position, this);
        }
        frame.runningProperty(g, properties, text);
    }

    @Override
    public void reset(int nx, int ny) {
    }

    @Override
    public void overlap() {
        frame.colorState.setState(AnimationState.OVERLAP);
    }

    @Override
    public void press() {
        action.action(this);
    }

    @Override
    public void unOverlap() {
        frame.colorState.setState(AnimationState.DEFAULT);
    }
}
