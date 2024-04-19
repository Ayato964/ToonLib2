package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.AnimationState;
import org.ayato.animation.Properties;
import org.ayato.util.IListenerDecoder;
import org.ayato.util.Position;

import java.awt.*;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

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
        g.fillRect(position.x.getAsInt() * text.MASTER.FRAME.DW, position.y.getAsInt()* text.MASTER.FRAME.DH -text.MASTER.FRAME.g.getFontMetrics().getHeight(),
                position.w* text.MASTER.FRAME.DW, position.h* text.MASTER.FRAME.DH);
        g.setColor(colorState.getState(AnimationState.FRAME));
        g.drawRect(position.x.getAsInt() * text.MASTER.FRAME.DW, position.y.getAsInt() * text.MASTER.FRAME.DH -text.MASTER.FRAME.g.getFontMetrics().getHeight(),
                position.w * text.MASTER.FRAME.DW, position.h * text.MASTER.FRAME.DH);
    }

    @Deprecated
    @Override
    public void reset(int nx, int ny) {}
}
