package org.ayato.system;

import org.ayato.system.properties.Properties;
import org.ayato.util.Display;

import java.awt.*;
import java.util.function.BooleanSupplier;

/**
 * <p>This Class relates to  {@link DisplayThread} and {@link Properties}.</p>
 *<p>This Class view the Strings TEXT on the  {@link Graphics} class  of {@link MyFrame} class .</p>
 * <p>See {@link Properties} to edit TEXT </p>
 *<p>This don't call constructor. <br>
 * for example:<code>AnimationText.create(ExcuseScene scene).draw(String s, int x, int y, Properties p)</code>
 * </p>
 *
 * @see DisplayThread
 * @see Properties
 * @see Graphics
 * @see MyFrame
 * @since 0.4.0
 * @apiNote Ayato
 */
public class AnimationText implements Display {
    private String mes;
    private int x, y;
    public final ExecuteScene MASTER;
    private Properties properties;
    public BooleanSupplier bool;
    private AnimationText(ExecuteScene scene){
        MASTER = scene;
    }

    /**
     *
     * @param scene Use ExecuteScene's instance you defined
     * @return new Instance of AnimationText.
     */
    @Deprecated(since = "0.4.5")
    public static AnimationText create(ExecuteScene scene){
        AnimationText text = new AnimationText(scene);
        scene.SCENE.addDisplay(text);
        return text;
    }

    public static AnimationText create(ExecuteScene scene, String str, int bx, int by, Properties properties){
        AnimationText t = new AnimationText(scene);
        scene.SCENE.addDisplay(t);
        t.mes = str;
        t.x = bx;
        t.y = by;
        t.properties = properties;
        t.bool = ()->true;

        if(properties != null)
            properties.addAnimation(t);
        return t;
    }


    @Deprecated(since = "0.4.5")
    public void draw(String str, int x, int y){
        draw(str, x, y, null);
    }

    @Deprecated(since = "0.4.5")
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
        if(s != null)
            mes = s;
    }

    public String getMES() {
        return mes;
    }
}
