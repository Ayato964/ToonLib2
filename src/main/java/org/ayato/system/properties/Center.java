package org.ayato.system.properties;

import org.ayato.system.AnimationText;

import java.awt.*;

public class Center implements IProperty{
    private int bx, by, bw, bh;
    private boolean isFirst = true;
    @Override
    public void runningProperty(Graphics g, Properties properties, AnimationText text) {
        if(isFirst){
            isFirst = false;
            int windowCenter = text.MASTER.FRAME.DESCTOP_BOUNDS.width / 2;
            int textCenter = getTextWidth(g, text.getMES()) / 2;
            text.setX((windowCenter - textCenter )/ text.MASTER.FRAME.DW);
        }
    }
    private int getTextWidth(Graphics g, String str){
        int w = 0;
        for(int i = 0;i < str.length(); i ++){
            w += g.getFontMetrics().charWidth(str.charAt(i));
        }
        return w;
    }
}
