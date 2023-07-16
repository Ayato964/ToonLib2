package org.ayato.animation;

import java.awt.*;

public class AnimationMouseButtonList<T extends AnimationList<?>>{
    T list;
    private boolean visible = false;

    private AnimationMouseButtonList(T t, int bx, int by, int bw, int bh){
        list = t;
        int mh = bh;
        bh = mh / list.length();
        for(int i = 0; i < list.length(); i ++){
            list.get(i).setup(bx, by + bh * i, list.get(i).properties
                            .ifView(()->visible)
                    .button(bx, by + bh * i, bw, bh,
                            ()->Color.WHITE,
                            new Color(127, 127, 127, 50),
                            null, list.get(i).action
                            ));
        }

    }
    public static <L extends AnimationList<?>> AnimationMouseButtonList<L> generate(L l, int bx, int by, int bw, int bh){
        return new AnimationMouseButtonList<>(l, bx, by, bw, bh);
    }

    public void setVisible(boolean b) {
        visible = b;
    }
}
