package org.ayato.animation;

import org.ayato.system.LunchScene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AnimationKeyButtons<A, T extends AnimationList<A, Properties<A>>> extends AbstractAnimations<A, T> implements KeyListener {

    private int selectCount = 0;
    public AnimationKeyButtons(T list, int x, int y, int w, int h, Color ifSelectedColor, Color normalColor){
        super(list, x, y, w, h, ifSelectedColor, normalColor);
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
        AnimationList<A, Properties<A>> n = list;
        while (n != null){
            final int c = count;
            ((Properties.TextProperties)n.getProperties()).ifView(()->visible).frame(w, one_height,
                    ()->c == selectCount ? selected : normal , Color.GRAY);
            Animation.create(MASTER, n.getNode(), n.getProperties().setSize(x, y + count * one_height));
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
            case KeyEvent.VK_ENTER:list.get(selectCount).drawAction();break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
