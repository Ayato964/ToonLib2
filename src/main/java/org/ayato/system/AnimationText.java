package org.ayato.system;

import org.ayato.animation.AbstractAnimation;
import org.ayato.animation.properties.TextProperties;

import java.awt.*;

/**
 * <p>This Class relates to  {@link DisplayThread} and {@link TextProperties}.</p>
 *<p>This Class view the Strings TEXT on the  {@link Graphics} class  of {@link MyFrame} class .</p>
 * <p>See {@link TextProperties} to edit TEXT </p>
 *<p>This don't call constructor. <br>
 * for example:<code>AnimationText.create(ExcuseScene scene).draw(String s, int x, int y, Properties p)</code>
 * </p>
 *
 * @see DisplayThread
 * @see TextProperties
 * @see Graphics
 * @see MyFrame
 * @since 0.4.0
 * @apiNote Ayato
 */
public class AnimationText extends AbstractAnimation<String>{

    private AnimationText(LunchScene scene){
        super(scene);
    }

    @Override
    protected void run(Graphics g, String o) {
        g.drawString(mes, x * MASTER.FRAME.DW, y * MASTER.FRAME.DH);
    }

    public static AnimationText create(LunchScene scene, String str, int bx, int by, TextProperties properties){
        AnimationText t = new AnimationText(scene);
        t.mes = str;
        t.x = bx;
        t.y = by;
        t.properties = properties;
        t.bool = ()->true;

        if(properties != null)
            properties.addAnimation(t);

        scene.SCENE.addDisplay(t);
        return t;
    }
}
