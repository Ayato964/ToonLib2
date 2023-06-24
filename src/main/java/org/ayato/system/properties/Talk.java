package org.ayato.system.properties;

import org.ayato.system.AnimationText;
import org.ayato.system.Component;
import org.ayato.system.ExecuteScene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class Talk implements IProperty, KeyListener {
    int count = 0;
    String[] mes;
    KeyListener[] listeners;
    private boolean stopEveryEvent, isFirst = true;
    private AnimationText ANIMATION;
    private Object percent;
    private PropertyAction action;
    public Talk(String[] strings, Object percent, boolean stopEveryEvent, PropertyAction action) {
        mes =strings;
        this.percent = percent;
        this.action = action;
        this.stopEveryEvent = stopEveryEvent;
    }

    @Override
    public void runningProperty(Graphics g, Properties properties, AnimationText text) {
        if(stopEveryEvent){
            stopEveryEvent = false;
            listeners = text.MASTER.FRAME.getKeyListeners();
            for(KeyListener l : listeners){
                text.MASTER.FRAME.removeKeyListener(l);
            }
        }
        if(isFirst){
            isFirst = false;
            ANIMATION = text;
            text.MASTER.FRAME.addKeyListener(this);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER || keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
            if(count < mes.length){
                ANIMATION.setMSG(Component.get(percent, mes[count]));
                count ++;
            }else{
                ANIMATION.MASTER.SCENE.removeDisplay(ANIMATION);
                ANIMATION.MASTER.FRAME.removeKeyListener(this);
                for(KeyListener l : listeners) ANIMATION.MASTER.FRAME.addKeyListener(l);
                if(action != null)
                    action.action(this);

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
