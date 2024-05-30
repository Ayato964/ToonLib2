package org.ayato.util;

import org.ayato.system.DisplayThread;
import org.ayato.system.LunchScene;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class TimeCounter implements  Runnable {
    private final CopyOnWriteArrayList<CounterHost> hosts = new CopyOnWriteArrayList<>();
    private static TimeCounter INSTANCE = new TimeCounter();
    public TimeCounter(){}

    public static void add(ITimeCounter host, long max){
        INSTANCE.hosts.add(new CounterHost(host, 0, max));
    }
    public static void remove(ITimeCounter counter){
        for(int i = 0; i < INSTANCE.hosts.size(); i ++){
            if(INSTANCE.hosts.get(i).host.equals(counter)){
                INSTANCE.hosts.remove(i);
                break;
            }
        }
    }
    public static void init(){
        Thread t = new Thread(INSTANCE);
        t.start();
    }

    public static void removeAll() {
        for(CounterHost h : INSTANCE.hosts){
            INSTANCE.hosts.remove(h);
        }
    }

    @Override
    public void run() {
        while (true){
            for(CounterHost host : hosts){
                host.now ++;
                if(host.now >= host.max){
                    //System.out.println(host.now + "   " + host.max);
                    host.host.timeTick();
                    host.now = 0;
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    protected static final class CounterHost{
        ITimeCounter host;
        long now;
        long max;
        CounterHost(ITimeCounter host, long now, long max){
            this.host = host;
            this.max = max;
            this.now = now;
        }
    }
}
