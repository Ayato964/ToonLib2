package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.system.Component;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Talk implements IProperty<String>, KeyListener {
    int count = 0;
    String[] mes;
    KeyListener[] listeners;
    private boolean stopEveryEvent, isFirst = true;
    private Animation<String> ANIMATION;
    private Object percent;
    private PropertyAction action;
    public Talk(String[] strings, Object percent, boolean stopEveryEvent, PropertyAction action) {
        mes =strings;
        this.percent = percent;
        this.action = action;
        this.stopEveryEvent = stopEveryEvent;
    }

    @Override
    public void runningProperty(Graphics g, Properties<String> properties, Animation<String> text) {
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
    public void reset(int nx, int ny) {
        count = 0;

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER || keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
            if(count < mes.length){
                ANIMATION.setViewObject(Component.get(percent, mes[count]));
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
