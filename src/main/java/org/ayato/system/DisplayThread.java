package org.ayato.system;

import org.ayato.util.Display;
import org.ayato.util.IBaseScene;
import org.ayato.util.VoidSupplier;

import java.awt.*;
import java.util.ArrayList;

class DisplayThread {
    private boolean isExecute = true;
    private final Thread thread;
    private final VoidSupplier sup;
    private final ArrayList<Display> displays;
    private final ExecuteScene MASTER;
    private final ArrayList<VoidSupplier> endTask;
    private DisplayThread(VoidSupplier voidSupplier, ExecuteScene scene){
        thread = new Thread(this::run);
        sup = voidSupplier;
        displays = new ArrayList<>();
        MASTER = scene;
        endTask = new ArrayList<>();
    }
    public static DisplayThread runThread(VoidSupplier supplier, ExecuteScene scene){
        DisplayThread t = new DisplayThread(supplier, scene);
        t.thread.start();
        return t;
    }
    private void run(){
        while (isExecute){
            //System.out.println("Hello World");
            for(Display d : displays)d.display(MASTER.GRAPHIC);

            MASTER.FRAME.repaint();


            if(sup != null)
                sup.action();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (VoidSupplier v : endTask) v.action();
            endTask.clear();
        }
    }
    public void addDisplay(Display display){
        displays.add(display);
    }
    public void addEndTask(VoidSupplier supplier){
        endTask.add(supplier);
    }
    public void removeDisplay(Display display){
        System.out.println(display);
        System.out.println(displays.get(1));
        for(int i = 0; i < displays.size(); i ++)
            if(display.equals(displays.get(i))) {
                displays.remove(i);
            }

    }
    public void removeDisplayClass(Class<?> cls){
        for(int i = 0; i < displays.size(); i ++){
            if(displays.get(i).getClass() == cls){
                System.out.println("remove animation text");
                displays.remove(i);
                i = -1;
            }
        }
    }
}
