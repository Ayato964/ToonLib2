package org.ayato.system;

import org.ayato.animation.Animation;
import org.ayato.util.Display;
import org.ayato.util.VoidSupplier;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class DisplayThread {
    private boolean isExecute = true;
    private final Thread thread;
    private final VoidSupplier sup;
    private final CopyOnWriteArrayList<Display> displays;
    private final CopyOnWriteArrayList<Display> GUIS = new CopyOnWriteArrayList<>();
    private final ToonMaster MASTER;
    private final CopyOnWriteArrayList<VoidSupplier> endTask;
    public final int SLEEP_TIME = 10;
    private DisplayThread(VoidSupplier voidSupplier, ToonMaster scene){
        thread = new Thread(this::run);
        sup = voidSupplier;
        displays = new CopyOnWriteArrayList<>();
        MASTER = scene;
        endTask = new CopyOnWriteArrayList<>();
    }
    public static DisplayThread runThread(VoidSupplier supplier, ToonMaster scene){
        DisplayThread t = new DisplayThread(supplier, scene);
        t.thread.start();
        return t;
    }
    private void run(){
        while (isExecute){
        //System.out.println("Hello World");
            for (Display d : displays) d.display(MASTER.GRAPHIC);

            for(Display d: GUIS) d.display(MASTER.GRAPHIC);

            MASTER.FRAME.repaint();


            if (sup != null)
                sup.action();

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }



            for (VoidSupplier v : endTask) v.action();
            endTask.clear();
        }
    }
    public ArrayList<ComponentTag> getTagComponents(){
        ArrayList<ComponentTag> tags = new ArrayList<>();
        for(Display d : displays){
            if(d instanceof ComponentTag){
                tags.add((ComponentTag) d);
            }
        }
        return tags;
    }
    public CopyOnWriteArrayList<Display> getDisplay(){
        return displays;
    }
    public void addDisplay(Display display){
            switch (display){
                case Animation<?> a: GUIS.add(a); break;
                default:
                    displays.add(display);break;
            }
    }
    public void addEndTask(VoidSupplier supplier){
        endTask.add(supplier);
    }
    public void removeDisplay(Display display){
        switch (display){
            case Animation<?> a-> GUIS.remove(a);
            default -> displays.remove(display);
        }
    }
    public void removeDisplayAll(){
        displays.clear();
        GUIS.clear();
    }

    public ArrayList<ComponentGroup> getGroupComponent() {
        ArrayList<ComponentGroup> groups = new ArrayList<>();
        for(Display dd : displays){
            if(dd instanceof  ComponentGroup){
                groups.add((ComponentGroup) dd);
            }
        }
        for(Display dd : GUIS){
            if(dd instanceof  ComponentGroup){
                groups.add((ComponentGroup) dd);
            }
        }
        return groups;
    }

    public <T extends Display> void addAllDisplay(ArrayList<T> objects) {
        for(Display toon : objects){
            boolean isEquals = false;
            for(Display d : displays){
                if(toon.getSerialID() == d.getSerialID()){
                    isEquals = true;
                }
            }
            if(!isEquals) {
                displays.add(toon);
            }
        }
    }
}
