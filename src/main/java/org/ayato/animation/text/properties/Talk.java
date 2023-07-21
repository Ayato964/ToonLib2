package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.system.Component;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.function.Supplier;

public class Talk implements IProperty<String>, KeyListener {
    int count = 0;
    Supplier<String>[] mes;
    KeyListener[] listeners;
    private final boolean stopEveryEventPercent;
    private boolean stopEveryEvent;
    private boolean isFirst = false;
    private Animation<String> ANIMATION;
    private Object percent;
    private PropertyAction action;
    public Talk(Supplier<String>[] strings, Object percent, boolean stopEveryEvent, PropertyAction action) {
        mes =strings;
        System.out.println(Arrays.toString(mes));
        stopEveryEventPercent = stopEveryEvent;
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
            text.setViewObject(mes[0].get());
            text.MASTER.FRAME.addKeyListener(this);
        }
    }

    @Override
    public void reset(int nx, int ny) {
        count = 0;
        isFirst = true;
        stopEveryEvent = stopEveryEventPercent;

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER || keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
            if(count < mes.length - 1 ){
                count ++;
                ANIMATION.setViewObject(mes[count].get());

            }else{
                ANIMATION.MASTER.SCENE.removeDisplay(ANIMATION);
                for(KeyListener l : listeners) ANIMATION.MASTER.FRAME.addKeyListener(l);
                ANIMATION.MASTER.FRAME.removeKeyListener(this);
                if(action != null)
                    action.action(this);

            }
        }
    }
    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
