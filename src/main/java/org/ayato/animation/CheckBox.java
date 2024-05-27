package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;
import org.ayato.util.IListenerDecoder;
import org.ayato.util.KeyInputs;
import org.ayato.util.MouseInputs;
import org.ayato.util.Position;

import java.awt.*;
import java.util.function.Consumer;

public class CheckBox implements IProperty, IListenerDecoder {
    private final Consumer<Boolean> buttonAction;
    private final Color  checkColor;
    private final AnimationState state;
    private final Duration duration;
    private final Position position;
    private boolean isClicked = false;
    public CheckBox(Consumer<Boolean> buttonAction, AnimationState state, Color checkColor, Duration duration){
        this.buttonAction = buttonAction;
        this.state = state;
        this.checkColor = checkColor;
        this.duration = duration;
        position = new Position(0, 0, 0, 0);
        MouseInputs.add(position, this);
        KeyInputs.add(this);
    }
    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
        Position p_pos = properties.position;
        switch (duration){
            case RIGHT -> {
                int newX = g.getFontMetrics().stringWidth((String) animation.mes);
                position.setX(p_pos.getX()+ 2 + newX / animation.MASTER.DW);
                position.setY(p_pos.getY());

            }
            case LEFT -> {
                position.setX(p_pos.getX() - g.getFontMetrics().getHeight() / animation.MASTER.DH - 2);
                position.setY(p_pos.getY());
            }
        }
        position.setW(g.getFontMetrics().getHeight() / animation.MASTER.DH);
        position.setH(g.getFontMetrics().getHeight() / animation.MASTER.DH);
        g.setColor(state.getState(AnimationState.FRAME));
        g.drawRect(position.getX() * animation.MASTER.DW, position.getY() * animation.MASTER.DH,
                position.getW() * animation.MASTER.DW, position.getH() * animation.MASTER.DH);
        if(isClicked) {
            g.setColor(checkColor);
            g.fillRect(position.getX() * animation.MASTER.DW, position.getY() * animation.MASTER.DH,
                    position.getW() * animation.MASTER.DW, position.getH() * animation.MASTER.DH);
        }
    }

    @Override
    public void overlap() {
        state.setState(AnimationState.OVERLAP);
    }

    @Override
    public void press() {
        isClicked = !isClicked;
        buttonAction.accept(isClicked);
    }

    @Override
    public void unOverlap() {
        state.setState(AnimationState.DEFAULT);
    }
    public enum Duration{
        LEFT,
        RIGHT
    }
}
