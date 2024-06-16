package org.ayato.system;

import java.util.concurrent.CopyOnWriteArrayList;

public final class TickThread implements Runnable{

    public static final TickThread INSTANCE = new TickThread();
    private boolean isRunning = true;
    private final CopyOnWriteArrayList<Tick> ticks = new CopyOnWriteArrayList<>();
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
    @Override
    public void run() {
        while (isRunning){
            if(!ticks.isEmpty())
                for(Tick tick : ticks)
                    tick.tick();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
