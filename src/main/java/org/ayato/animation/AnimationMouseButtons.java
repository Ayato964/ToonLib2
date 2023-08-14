package org.ayato.animation;

import java.awt.*;

public class AnimationMouseButtons<A, T extends AnimationList<A, Properties>> extends  AbstractAnimations<A, T>{

    public AnimationMouseButtons(T list, int x, int y, int w, int h, Color ifSelectedColor, Color normalColor, Color bgC) {
        super(list, x, y, w, h, ifSelectedColor, normalColor, bgC);
    }


    @Override
    void setup() {
        int count = 0;
        final int one_height = (h * MASTER.FRAME.DH / list.length()) / MASTER.FRAME.DH;
        T node = list;
        while (node != null) {
            final T n = node;
            Animation.create(MASTER, node.getNode(), node.getProperties().setSize(x, y + count * MASTER.FRAME.DH), true);
            ((TextProperties) node.getProperties())
                    .ifView(()->visible)
                    .button(w, one_height, ()-> selected, bgColor, null, property ->  n.drawAction());

            node = (T) node.next();
            count ++;
        }
    }
}
