package org.ayato.system.properties;

import org.ayato.system.AnimationText;
import org.ayato.system.ExecuteScene;

import java.awt.*;

public class Frame implements IProperty{
    int bx, by, bw, bh;
    Color frameCol, backCol;

    public Frame(int bx, int by, int bw, int bh, Color frameCol, Color backColor) {
        this.bx = bx;
        this.by = by;
        this.bw = bw;
        this.bh = bh;
        this.frameCol = frameCol;
        this.backCol = backColor;

    }

    @Override
    public void runningProperty(Graphics g, Properties properties, AnimationText text){
        g.setColor(backCol);
        g.fillRect(bx * text.MASTER.FRAME.DW, by * text.MASTER.FRAME.DH  - g.getFontMetrics().getHeight(), bw * text.MASTER.FRAME.DW, bh* text.MASTER.FRAME.DH);
        g.setColor(frameCol);
        g.drawRect(bx * text.MASTER.FRAME.DW, by * text.MASTER.FRAME.DH  - g.getFontMetrics().getHeight(), bw * text.MASTER.FRAME.DW, bh* text.MASTER.FRAME.DH);
    }
}
