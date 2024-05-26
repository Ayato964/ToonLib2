package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;

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
    public void runningProperty(Graphics g, Properties properties, Animation<?> text) {
        int windowCenter = text.MASTER.FRAME.DESCTOP_BOUNDS.width / 2;
        int frameCenter = bw * text.MASTER.DW / 2;
        int bx = (windowCenter - frameCenter) / text.MASTER.DW;
        g.setColor(bc);
        g.fillRect(bx * text.MASTER.DW, by * text.MASTER.DH - g.getFontMetrics().getHeight(),
                bw * text.MASTER.DW, bh * text.MASTER.DH );
        g.setColor(fc.get());
        g.drawRect(bx * text.MASTER.DW, by * text.MASTER.DH - g.getFontMetrics().getHeight(),
                bw * text.MASTER.DW, bh * text.MASTER.DH);
    }


}
