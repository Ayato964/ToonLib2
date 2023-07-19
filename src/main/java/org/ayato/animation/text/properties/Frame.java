package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;

import java.awt.*;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Frame implements IProperty<String>{
    IntSupplier bx, by;
    int bw, bh;
    Color backCol;
    Supplier<Color> frameCol;

    public Frame(IntSupplier bx, IntSupplier by, int bw, int bh, Supplier<Color> frameCol, Color backColor) {
        this.bx = bx;
        this.by = by;
        this.bw = bw;
        this.bh = bh;
        this.frameCol = frameCol;
        this.backCol = backColor;

    }

    @Override
    public void runningProperty(Graphics g, Properties<String> properties, Animation<String> text){
        g.setColor(backCol);
        g.fillRect(bx.getAsInt() * text.MASTER.FRAME.DW, by.getAsInt() * text.MASTER.FRAME.DH  - g.getFontMetrics().getHeight(),
                bw * text.MASTER.FRAME.DW, bh* text.MASTER.FRAME.DH);
        g.setColor(frameCol.get());
        g.drawRect(bx.getAsInt() * text.MASTER.FRAME.DW, by.getAsInt() * text.MASTER.FRAME.DH  - g.getFontMetrics().getHeight(),
                bw * text.MASTER.FRAME.DW, bh* text.MASTER.FRAME.DH);
    }

    @Override
    public void reset(int nx, int ny) {

    }
}
