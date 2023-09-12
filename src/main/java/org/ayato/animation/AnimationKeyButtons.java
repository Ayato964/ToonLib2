package org.ayato.animation;

import org.ayato.system.LunchScene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.BooleanSupplier;

public class AnimationKeyButtons<A, T extends AnimationList<A, Properties>> extends AbstractAnimations<A, T> implements KeyListener {

    private int selectCount = 0;
    public AnimationKeyButtons(T list, int x, int y, int w, int h, Color ifSelectedColor, Color normalColor, Color bgColor){
        super(list, x, y, w, h, ifSelectedColor, normalColor, bgColor);
    }

    @Override
    protected void visibleAction(boolean v) {
        if(v)
            MASTER.FRAME.addKeyListener(this);
        else
            MASTER.FRAME.removeKeyListener(this);
    }

    protected void setup(){
        int one_height = (h * MASTER.FRAME.DH / list.length()) /MASTER.FRAME.DH;
        int count = 0;
        AnimationList<A, Properties> n = list;
        while (n != null){
            final int c = count;

            n.setKey(this);
            ((TextProperties)n.getProperties()).ifView(()->visible).frame(w, one_height,
                    ()->c == selectCount ? selected : normal , bgColor);
            Animation.create(MASTER, n.getNode(), n.getProperties().setSize(x, y + count * one_height), true);
            count ++;
            n =  n.next();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP : if(selectCount > 0) selectCount --; else selectCount = list.length() - 1; break;
            case KeyEvent.VK_DOWN:if(selectCount < list.length() -1) selectCount ++; else selectCount = 0; break;
            case KeyEvent.VK_ENTER:list.get(selectCount).drawAction();MASTER.FRAME.removeKeyListener(this);break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
