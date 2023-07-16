package org.ayato.animation.properties;

import org.ayato.AbstractProperties;
import org.ayato.system.AnimationText;

import java.awt.*;

public class Center implements IProperty<AnimationText, TextProperties>{
    private int bx, by, bw, bh;
    private boolean isFirst = true;
    private int getTextWidth(Graphics g, String str){
        int w = 0;
        for(int i = 0;i < str.length(); i ++){
            w += g.getFontMetrics().charWidth(str.charAt(i));
        }
        return w;
    }

    @Override
    public void runningProperty(Graphics g, TextProperties properties, AnimationText text) {
        if(isFirst){
            isFirst = false;
            int windowCenter = text.MASTER.FRAME.DESCTOP_BOUNDS.width / 2;
            int textCenter = getTextWidth(g, text.getViewObject()) / 2;
            text.setX((windowCenter - textCenter )/ text.MASTER.FRAME.DW);
        }
    }
}
