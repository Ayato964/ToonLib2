package org.ayato.animation;

import org.ayato.animation.text.properties.ChangeFunction;
import org.ayato.animation.text.properties.IProperty;
import org.ayato.util.IListenerDecoder;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Change<T> implements IProperty, MouseListener, KeyListener {
    private final ChangeFunction<T> condition;
    private KeyEvent event;
    private boolean isClicked;
    private int count = 0;
    public Change(ChangeFunction<T> condition) {
        this.condition = condition;
    }

    @Override
    public void setupProperty(Graphics g, Properties<?> properties, Animation<?> animation) {
        animation.MASTER.FRAME.addKeyListener(this);
        animation.MASTER.FRAME.addMouseListener(this);
    }

    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {

        Animation<T> animation1 = (Animation<T>) animation;
        T t = condition.getCondition(isClicked, event, count);
        if(t != null) {
            animation1.setViewObject(t);
            count ++;
        }
        event = null;
        isClicked = false;
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        event = keyEvent;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        isClicked = true;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
