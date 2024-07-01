package org.ayato.system;

import org.ayato.util.Display;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

final class Background implements Display {
    private final long serial = new Random().nextLong(0, 1000000);
    private static final Background INSTANCE = new Background();
    private BaseBackground background;

    private Background(){
        /*
        background = new BaseBackground() {
            @Override
            public void setLayer(ArrayList<Layer> layers) {
                layers.add((g, w, h)->{
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, w, h);
                });
            }
        };

         */
    }

    public static Background getINSTANCE() {
        return INSTANCE;
    }
    public void setBackground(BaseBackground background){
        this.background = background;
    }

    @Override
    public void display(Graphics g) {
        background.display((Graphics2D) g, (int) ToonMaster.getINSTANCE().FRAME.DESCTOP_BOUNDS.getWidth(),
                (int) ToonMaster.getINSTANCE().FRAME.DESCTOP_BOUNDS.getHeight());
    }


    @Override
    public long getSerialID() {
        return serial;
    }
}
