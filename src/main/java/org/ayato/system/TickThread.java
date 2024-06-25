package org.ayato.system;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public final class TickThread implements Runnable{

    public static final TickThread INSTANCE = new TickThread();
    private boolean isRunning = true;
    private final CopyOnWriteArrayList<Tick> ticks = new CopyOnWriteArrayList<>();
    public static int RATE = 1;
    private TickThread(){
        Thread n = new Thread(this);
        n.start();
    }

    public TickThread getInstance(){
        return INSTANCE;
    }
    public void add(Tick t){
        ticks.add(t);
    }
    public <T extends Tick> void addAll(ArrayList<T> objects){
        for(Tick toon : objects){
            boolean isEquals = false;
            for(Tick t : ticks){
                if(toon.getSerialID() == t.getSerialID()){
                    isEquals = true;
                }
            }
            if(!isEquals)
                ticks.add(toon);
        }
    }
    @Override
    public void run() {
        while (isRunning){
            if(!ticks.isEmpty())
                for(Tick tick : ticks)
                    tick.tick();

            try {
                Thread.sleep(RATE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int size() {

        return ticks.size();
    }

    public void removeAll() {
        ticks.clear();
    }

    public void remove(Tick object) {
        ticks.remove(object);
    }
}
