package org.ayato.util;

import org.ayato.system.LunchScene;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.FieldPosition;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MouseInputs implements MouseListener, Runnable {
    private static final HashMap<Position, IListenerDecoder> DECODER = new HashMap<>();
    private static final CopyOnWriteArrayList<Position> POSITION = new CopyOnWriteArrayList<>();
    private static final MouseInputs INSTANCE = new MouseInputs();
    private boolean isRunning = false;
    private LunchScene MAIN;
    public static void init(LunchScene m){
        INSTANCE.isRunning = true;
        INSTANCE.MAIN = m;
        m.FRAME.addMouseListener(INSTANCE);
        Thread t = new Thread(INSTANCE);
        t.start();
    }
    public static void add(Position position, IListenerDecoder dec){
        POSITION.add(position);
        DECODER.put(position, dec);
    }
    public static void remove(Position p){
        POSITION.remove(p);
        DECODER.remove(p);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(isRunning) {
            for (Position p : POSITION) {
                if (p.isInRect(mouseEvent.getX(), mouseEvent.getY(), MAIN)) {
                    DECODER.get(p).press();
                }
            }
        }else{
            KeyInputs.running(false);
            isRunning = true;
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
            if (isRunning) {
                Point point = MouseInfo.getPointerInfo().getLocation();
                for (Position p : POSITION) {
                    if (p.isInRect(point.x, point.y, MAIN))
                        DECODER.get(p).overlap();
                    else
                        DECODER.get(p).unOverlap();
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
