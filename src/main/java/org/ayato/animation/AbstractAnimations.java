package org.ayato.animation;

import org.ayato.system.LunchScene;

import java.awt.*;

public abstract class AbstractAnimations<A, T extends AnimationList<A, Properties<A>>> {
    protected final T list;
    protected boolean visible = false;
    protected int x, y, w, h;
    protected final LunchScene MASTER;
    protected Color selected, normal;

    public AbstractAnimations(T list, int x, int y, int w, int h, Color ifSelectedColor, Color normalColor){
        this.list = list;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        MASTER = list.MASTER;
        selected = ifSelectedColor;
        normal = normalColor;
        setup();
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
        visibleAction(visible);
    }
    protected void visibleAction(boolean b){}

    abstract void setup();
}
