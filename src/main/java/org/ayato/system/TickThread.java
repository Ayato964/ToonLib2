package org.ayato.system;

import org.ayato.util.ArrayUtils;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public final class TickThread implements Runnable{

    public static final TickThread INSTANCE = new TickThread();
    private boolean isRunning = true;
    public boolean isLocked = false;
    private final CopyOnWriteArrayList<Tick> ticks = new CopyOnWriteArrayList<>();
    public static int RATE = 1;
    private final CopyOnWriteArrayList<Tick> LOCK_LIST = new CopyOnWriteArrayList<>();


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
                    if(!isLocked)
                        tick.tick();
                    else if(!ArrayUtils.isFindObject(tick, LOCK_LIST))
                        tick.tick();

            try {
                Thread.sleep(RATE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void setLockStatesForTags(boolean isException, String... tags){
        ArrayList<Tick> lock_list = ArrayUtils.searchObjectForTag(ticks, tags);
        setLockList(isException, lock_list);
    }
    public void setLockStatesForGroup(boolean isException, String group){
        ArrayList<Tick> lock_list = ArrayUtils.searchObjectForGroup(ticks, group);
        setLockList(isException, lock_list);
    }
    private void setLockList(boolean isException, ArrayList<Tick> lock_list){
        if(!isException)
            LOCK_LIST.addAll(lock_list);
        else{
            for(Tick t: ticks){
                if(!ArrayUtils.isFindObject(t, lock_list))
                    LOCK_LIST.add(t);
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

    public void clearLockConfig() {
        isLocked = false;
        LOCK_LIST.clear();
    }
}
