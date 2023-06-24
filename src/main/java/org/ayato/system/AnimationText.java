package org.ayato.system;

import org.ayato.system.properties.Properties;
import org.ayato.util.Display;

import java.awt.*;

public class AnimationText implements Display {
    private String mes;
    private int x, y;
    public final ExecuteScene MASTER;
    private Properties properties;
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
        if(properties != null)
            properties.addAnimation(this);
    }

    @Override
    public void display(Graphics g) {

        if(properties != null)
            properties.runProp(g);
        g.drawString(mes, x * MASTER.FRAME.DW, y * MASTER.FRAME.DH);
    }
}
