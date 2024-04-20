package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class Input implements IProperty, MouseListener, KeyListener {
    private int x, y, w, h;
    private StringBuilder inputStr = new StringBuilder().append('>');
    private Consumer<String> stringConsumer;
    private boolean isFirst = true, isInputAccepted = false;
    private Animation<?> ANIMATION;

    public Input(int bx, int by, int bw, int bh, Consumer<String> stringConsumer) {
        this.stringConsumer = stringConsumer;
        x = bx;
        y = by;
        w = bw;
        h = bh;
    }

    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> text) {
        Animation<String> t = (Animation<String>)text;
        if(isFirst){
            x = (x * text.MASTER.DW);
            y = (y * text.MASTER.DH);
            w = (w * text.MASTER.DW);
            h = (h * text.MASTER.DH);
            isFirst = false;
            ANIMATION = text;
            text.MASTER.FRAME.addMouseListener(this);
        }
        if(isInputAccepted)
            t.setViewObject(inputStr.toString());
    }

    @Override
    public void reset(int nx, int ny) {
        x = nx;
        y = ny;
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
