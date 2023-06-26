package org.ayato.system;

import java.awt.*;

public class AnimationMouseButtonList<T extends AnimationList<?>>{
    T list;
    private final int mx, my, mw, mh;
    private boolean visible = false;

    private AnimationMouseButtonList(T t, int bx, int by, int bw, int bh){
        list = t;
        mx = bx;
        my = by;
        mw = bw;
        mh = bh;
        bh = mh / list.length();
        for(int i = 0; i < list.length(); i ++){
            list.get(i).setup(mx, my + bh * i, list.get(i).properties
                            .ifView(()->visible)
                    .button(mx, my + bh * i, mw, bh,
                            Color.WHITE,
                            new Color(127, 127, 127, 50),
                            null, list.get(i).action
                            ));
        }

    }
    public static <L extends AnimationList<?>> AnimationMouseButtonList<L> generate(L l, int bx, int by, int bw, int bh){
        AnimationMouseButtonList<L> g = new AnimationMouseButtonList<>(l, bx, by, bw, bh);
        return g;
    }

    public void setVisible(boolean b) {
        visible = b;
    }
}
