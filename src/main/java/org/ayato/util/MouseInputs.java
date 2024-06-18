package org.ayato.util;

import org.ayato.component.Position;
import org.ayato.component.Transform;
import org.ayato.system.LunchScene;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MouseInputs implements MouseListener, Runnable {
    private static final HashMap<Transform, IListenerDecoder> DECODER = new HashMap<>();
    private static final CopyOnWriteArrayList<Transform> TRANSFORMS = new CopyOnWriteArrayList<>();
    private static final MouseInputs INSTANCE = new MouseInputs();
    private boolean isRunning = false;
    private boolean isLocked = false;
    public static void init(LunchScene m){
        INSTANCE.isRunning = true;
        m.FRAME.addMouseListener(INSTANCE);
        Thread t = new Thread(INSTANCE);
        t.start();
    }
    public static void add(Transform transform, IListenerDecoder dec){
        TRANSFORMS.add(transform);
        DECODER.put(transform, dec);
    }
    public static void remove(Position p){
        TRANSFORMS.remove(p);
        DECODER.remove(p);
    }
    public static void setLock(boolean b){
        INSTANCE.isLocked = b;
    }

    public static void removeAll() {
        for(Transform p : TRANSFORMS){
            TRANSFORMS.remove(p);
        }
        DECODER.clear();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(!isLocked) {
            if (isRunning) {
                for (Transform p : TRANSFORMS) {
                    if (p.isCollision(mouseEvent.getX(), mouseEvent.getY())) {
                        DECODER.get(p).press();
                    }
                }
            } else {
                KeyInputs.running(false);
                isRunning = true;
            }
        }
    }
    public static void running(boolean b){
        INSTANCE.isRunning = b;
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

    @Override
    public void run() {
        while (true) {
            if(!isLocked) {
                if (isRunning) {
                    Point point = MouseInfo.getPointerInfo().getLocation();
                    for (Transform p : TRANSFORMS) {
                        if (p.isCollision((int) point.getX(), (int) point.getY()))
                            DECODER.get(p).overlap();
                        else
                            DECODER.get(p).unOverlap();
                    }
                }
                try {
                    Thread.currentThread().sleep(100L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
