package org.ayato.animation.text.properties;

import org.ayato.AbstractProperties;
import org.ayato.animation.AbstractAnimation;
import org.ayato.animation.text.AnimationText;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button<T extends AbstractAnimation<?>, M extends AbstractProperties<T>> implements IProperty<T, M>, MouseListener {
    int bx, by, bw, bh;
    PropertyAction insert, action;
    private boolean isFirst = true;

    public Button(int bx, int by, int bw, int bh, PropertyAction insert, PropertyAction action) {
        this.bx = bx;
        this.by = by;
        this.bw = bw;
        this.bh = bh;
        this.insert = insert;
        this.action = action;
    }

    @Override
    public void runningProperty(Graphics g, M properties, T text) {
        if(isFirst){
            isFirst = false;
            bx = (bx * text.MASTER.FRAME.DW );
            by = (by * text.MASTER.FRAME.DH - g.getFontMetrics().getHeight());
            bw = (bw * text.MASTER.FRAME.DW);
            bh = (bh * text.MASTER.FRAME.DH);
            text.MASTER.FRAME.addMouseListener(this);

        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getX() >= bx && mouseEvent.getX() <= bx + bw && mouseEvent.getY() >= by && mouseEvent.getY() <= by + bh){
            action.action(this);
        }
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
