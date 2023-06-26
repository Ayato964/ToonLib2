package org.ayato.system.properties;

import org.ayato.system.AnimationText;
import org.ayato.util.VoidSupplier;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button implements IProperty, MouseListener {
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
    public void runningProperty(Graphics g, Properties properties, AnimationText text) {
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
