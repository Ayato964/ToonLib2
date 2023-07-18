package org.ayato.animation;

import java.awt.*;

public class AnimationMouseButtons<A, T extends AnimationList<A, Properties<A>>> extends  AbstractAnimations<A, T>{

    public AnimationMouseButtons(T list, int x, int y, int w, int h, Color ifSelectedColor, Color normalColor) {
        super(list, x, y, w, h, ifSelectedColor, normalColor);
    }


    @Override
    void setup() {
        int count = 0;
        final int one_height = (h * MASTER.FRAME.DH / list.length()) / MASTER.FRAME.DH;
        T node = list;
        while (node != null) {
            final T n = node;
            Animation.create(MASTER, node.getNode(), node.getProperties().setSize(x, y + count * MASTER.FRAME.DH));
            ((Properties.TextProperties) node.getProperties())
                    .ifView(()->visible)
                    .button(w, one_height, ()-> selected, normal, null, property ->  n.drawAction());

            node = (T) node.next();
            count ++;
        }
    }
}
