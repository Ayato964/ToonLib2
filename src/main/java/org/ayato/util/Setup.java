package org.ayato.util;

import org.ayato.system.LunchScene;

import java.util.ArrayList;

public interface Setup{
    abstract void createUI(LunchScene scene);

    public default void runSetupClass(LunchScene scene){
        ArrayList<Setup> setups = new ArrayList<>();
        setups.add(this);
        setupUIClass(setups);
        for(Setup s : setups)
            s.createUI(scene);
    }

    default void setupUIClass(ArrayList<Setup> setups){}
}
