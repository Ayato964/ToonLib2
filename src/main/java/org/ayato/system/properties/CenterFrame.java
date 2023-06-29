package org.ayato.system.properties;

import org.ayato.system.AnimationText;

import java.awt.*;
import java.util.function.Supplier;

public class CenterFrame implements IProperty {
    int by, bw, bh;
    Supplier<Color> fc;
    Color bc;
    public CenterFrame(int by, int bw, int bh, Supplier<Color> frameCol, Color backColor) {
        this.by = by;
        this.bw = bw;
        this.bh = bh;
        fc = frameCol;
        bc = backColor;

    }

    @Override
    public void runningProperty(Graphics g, Properties properties, AnimationText text) {
        int windowCenter = text.MASTER.FRAME.DESCTOP_BOUNDS.width / 2;
        int frameCenter = bw * text.MASTER.FRAME.DW / 2;
        int bx = (windowCenter - frameCenter) / text.MASTER.FRAME.DW;
        g.setColor(bc);
        g.fillRect(bx * text.MASTER.FRAME.DW, by * text.MASTER.FRAME.DH - g.getFontMetrics().getHeight(),
                bw * text.MASTER.FRAME.DW, bh * text.MASTER.FRAME.DH );
        g.setColor(fc.get());
        g.drawRect(bx * text.MASTER.FRAME.DW, by * text.MASTER.FRAME.DH - g.getFontMetrics().getHeight(),
                bw * text.MASTER.FRAME.DW, bh * text.MASTER.FRAME.DH);
    }
}
