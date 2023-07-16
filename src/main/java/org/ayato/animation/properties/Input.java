package org.ayato.animation.properties;

import org.ayato.system.AnimationText;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class Input implements IProperty<AnimationText, TextProperties>, MouseListener, KeyListener {
    private int x, y, w, h;
    private StringBuilder inputStr = new StringBuilder().append('>');
    private Consumer<String> stringConsumer;
    private boolean isFirst = true, isInputAccepted = false;
    private AnimationText ANIMATION;

    public Input(int bx, int by, int bw, int bh, Consumer<String> stringConsumer) {
        this.stringConsumer = stringConsumer;
        x = bx;
        y = by;
        w = bw;
        h = bh;
    }

    @Override
    public void runningProperty(Graphics g, TextProperties properties, AnimationText text) {
        if(isFirst){
            x = (x * text.MASTER.FRAME.DW);
            y = (y * text.MASTER.FRAME.DH);
            w = (w * text.MASTER.FRAME.DW);
            h = (h * text.MASTER.FRAME.DH);
            isFirst = false;
            ANIMATION = text;
            text.MASTER.FRAME.addMouseListener(this);
        }
        if(isInputAccepted)
            text.setViewObject(inputStr.toString());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getX() >= x && e.getX() <= x + w && e.getY() >= y - h && e.getY() <= y + h){
            ANIMATION.MASTER.FRAME.addKeyListener(this);
            isInputAccepted = true;
        }else {
            ANIMATION.MASTER.FRAME.removeKeyListener(this);
            isInputAccepted = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() != 8) {
            inputStr.append(e.getKeyChar());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            ANIMATION.MASTER.FRAME.removeKeyListener(this);
            isInputAccepted = false;
            stringConsumer.accept(inputStr.substring(1));
        }
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && inputStr.length() - 1 > 0)
            inputStr.deleteCharAt(inputStr.length() - 1);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
