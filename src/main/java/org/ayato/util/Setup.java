package org.ayato.util;

import org.ayato.system.ToonMaster;

import java.util.ArrayList;

public interface Setup{
    abstract void createUI(ToonMaster scene);

    public default void runSetupClass(ToonMaster scene){
        ArrayList<Setup> setups = new ArrayList<>();
        setups.add(this);
        setupUIClass(setups);
        for(Setup s : setups)
            s.createUI(scene);
    }

    default void setupUIClass(ArrayList<Setup> setups){}
}
