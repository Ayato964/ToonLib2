package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.AnimationState;
import org.ayato.animation.Properties;
import org.ayato.component.Transform;
import org.ayato.component.Vector2D;
import org.ayato.util.IListenerDecoder;
import org.ayato.util.KeyInputs;
import org.ayato.util.MouseInputs;
import org.ayato.component.Position;

import java.awt.*;
import java.util.function.Consumer;

public class CheckBox implements IProperty, IListenerDecoder {
    protected final Consumer<Boolean> buttonAction;
    protected final Color  checkColor;
    protected final AnimationState state;
    private final Duration duration;
    protected final Transform transform;
    protected boolean isClicked = false;
    public CheckBox(Consumer<Boolean> buttonAction, AnimationState state, Color checkColor, Duration duration){
        this.buttonAction = buttonAction;
        this.state = state;
        this.checkColor = checkColor;
        this.duration = duration;
        transform = new Transform(0, 0, 0, 0);
        MouseInputs.add(transform, this);
        KeyInputs.add(this);
    }
    @Override
    public void runningProperty(Graphics2D og, Graphics g, Properties properties, Animation<?> animation) {
        Position p_pos = properties.transform.position;
        Vector2D vec = transform.getPosition();
        positionUpdate(p_pos, og, animation);
        g.setColor(state.getState(AnimationState.FRAME));
        g.drawRect(vec.x(), vec.y(), transform.getW() , transform.getH());
        if(isClicked) {
            g.setColor(checkColor);
            g.fillRect(vec.x() + animation.MASTER.DW, vec.y() + animation.MASTER.DH,
                    transform.getW() - 2 * animation.MASTER.DW, transform.getH() -2 * animation.MASTER.DW);
        }
    }
    protected void positionUpdate(Position p_pos, Graphics2D g, Animation<?> animation){
        switch (duration){
            case RIGHT -> {
                int newX = g.getFontMetrics().stringWidth((String) animation.mes);
                transform.position.setX(p_pos.getNormalX()+ 2 + newX / animation.MASTER.DW);
                transform.position.setY(p_pos.getNormalY());

            }
            case LEFT -> {
                transform.position.setX(p_pos.getNormalX() - g.getFontMetrics().getHeight() / animation.MASTER.DH - 2);
                transform.position.setY(p_pos.getNormalY());
            }
        }
        transform.scale.setW(g.getFontMetrics().getHeight() / animation.MASTER.DH);
        transform.scale.setH(g.getFontMetrics().getHeight() / animation.MASTER.DH);
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
