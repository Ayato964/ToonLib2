package org.ayato.system;

import org.ayato.system.properties.Properties;
import org.ayato.util.Display;

import java.awt.*;
import java.util.function.BooleanSupplier;

public class AnimationText implements Display {
    private String mes;
    private int x, y;
    public final ExecuteScene MASTER;
    private Properties properties;
    public BooleanSupplier bool;
    private AnimationText(ExecuteScene scene){
        MASTER = scene;
    }


    public static AnimationText create(ExecuteScene scene){
        AnimationText text = new AnimationText(scene);
        scene.SCENE.addDisplay(text);
        return text;
    }
    public void draw(String str, int x, int y){
        draw(str, x, y, null);
    }
    public void draw(String str, int x, int y, Properties properties){
        mes = str;
        this.x = x;
        this.y = y;
        this.properties = properties;
        this.bool = ()->true;

        if(properties != null)
            properties.addAnimation(this);
    }

    @Override
    public void display(Graphics g) {
        if(bool.getAsBoolean()) {
            if (properties != null)
                properties.runProp(g);
            g.drawString(mes, x * MASTER.FRAME.DW, y * MASTER.FRAME.DH);
        }
    }

    public void setMSG(String s) {
        mes = s;
    }
}
