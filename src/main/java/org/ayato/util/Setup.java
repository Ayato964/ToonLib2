package org.ayato.util;

import org.ayato.system.LunchScene;

import java.util.ArrayList;

public interface Setup extends SerialID{
    abstract void setup(LunchScene scene);

    public default void runSetupClass(LunchScene scene){
        ArrayList<Setup> setups = new ArrayList<>();
        setups.add(this);
        setupClass(setups);
        for(Setup s : setups)
            s.setup(scene);
    }

    default void setupClass(ArrayList<Setup> setups){}
}
