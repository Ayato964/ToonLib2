package org.ayato.system;

import java.util.ArrayList;

public interface Tick extends Serial, ComponentTag, ComponentGroup {
    void tick();

    default void runTickClass(TickThread disThread){
        final ArrayList<Tick> ticks = new ArrayList<>();
        ticks.add(this);
        tickClass(ticks);
        for(Tick d : ticks){
            disThread.add(d);
        }
    }
    default void tickClass(ArrayList<Tick> ticks){}
    @Override
    long getSerialID();
}
