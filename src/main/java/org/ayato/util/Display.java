package org.ayato.util;

import org.ayato.system.DisplayThread;

import java.awt.*;
import java.util.ArrayList;

public interface Display{
    abstract void display(Graphics g);

    default void runDisplayClass(DisplayThread disThread){
        final ArrayList<Display> displays = new ArrayList<>();
        displays.add(this);
        displayClass(displays);
        for(Display d : displays){
            disThread.addDisplay(d);
        }
    }
    default void displayClass(ArrayList<Display> display){}
    abstract long getSerialID();
}
