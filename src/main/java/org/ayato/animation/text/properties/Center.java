package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;

import java.awt.*;

public class Center implements IProperty<String>{
    private boolean isFirst = true;
    private int getTextWidth(Graphics g, String str){
        int w = 0;
        for(int i = 0;i < str.length(); i ++){
            w += g.getFontMetrics().charWidth(str.charAt(i));
        }
        return w;
    }

    @Override
    public void runningProperty(Graphics g, Properties<String> properties, Animation<String> text) {
        if(isFirst){
            isFirst = false;
            int windowCenter = text.MASTER.FRAME.DESCTOP_BOUNDS.width / 2;
            int textCenter = getTextWidth(g, text.getViewObject()) / 2;
            properties.setX((windowCenter - textCenter )/ text.MASTER.FRAME.DW);
        }
    }
}
