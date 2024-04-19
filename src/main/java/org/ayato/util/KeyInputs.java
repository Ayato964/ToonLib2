package org.ayato.util;

import org.ayato.system.LunchScene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class KeyInputs implements KeyListener, Runnable {
    public boolean isRunning = false;
    private static final HashMap<Integer, Boolean> KEY = new HashMap<>();
    private static final CopyOnWriteArrayList<IListenerDecoder> LISTENERS = new CopyOnWriteArrayList<>();
    private static final KeyInputs INSTANCE = new KeyInputs();
    private int ID = -1;
    private KeyInputs(){}

    public static void init(LunchScene scene){
        scene.FRAME.addKeyListener(INSTANCE);
        KEY.put(KeyEvent.VK_UP, false);
        KEY.put(KeyEvent.VK_DOWN, false);
        KEY.put(KeyEvent.VK_LEFT, false);
        KEY.put(KeyEvent.VK_RIGHT, false);
        INSTANCE.ID = -1;
        Thread thread = new Thread(INSTANCE);
        thread.start();
    }
    public static void add(IListenerDecoder l){
        LISTENERS.add(l);
    }
    public static void remove(IListenerDecoder l){
        LISTENERS.remove(l);
    }
    public static boolean get(int e){
        return KEY.get(e);
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if(isRunning){
            if(keyEvent.getExtendedKeyCode() == KeyEvent.VK_TAB || keyEvent.getKeyCode() == KeyEvent.VK_TAB) {
                if (keyEvent.isShiftDown()) {
                    if (ID > 0) ID--;
                    else ID = 0;
                } else {
                    if (ID + 1 >= LISTENERS.size()) ID = 0;
                    else ID++;
                }
            }
        }else{
            if(keyEvent.getExtendedKeyCode() == KeyEvent.VK_TAB || keyEvent.getKeyCode() == KeyEvent.VK_TAB){
                running(true);
                MouseInputs.running(false);
            }
        }
    }
    public static void running(boolean b){
        INSTANCE.isRunning = b;
        if(INSTANCE.isRunning)
            INSTANCE.ID = 0;
        else
            INSTANCE.ID = -1;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(isRunning) {
            KEY.put(keyEvent.getKeyCode(), true);
            if (ID != -1 && (keyEvent.getExtendedKeyCode() == KeyEvent.VK_ENTER ||
                    keyEvent.getKeyCode() == KeyEvent.VK_ENTER))
                LISTENERS.get(ID).press();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        KEY.put(keyEvent.getKeyCode(), false);
    }

    @Override
    public void run() {
        while (true){
            if(isRunning) {
                if (LISTENERS.size() != 0) {
                    if (ID != -1) {
                        LISTENERS.get(ID).overlap();
                    }
                    for (int i = 0; i < LISTENERS.size(); i++) {
                        if (i != ID)
                            LISTENERS.get(i).unOverlap();
                    }
                }
            }
            try {
                Thread.currentThread().sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
