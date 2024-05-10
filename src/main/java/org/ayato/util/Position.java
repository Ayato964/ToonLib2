package org.ayato.util;

import org.ayato.system.LunchScene;

import java.util.ArrayList;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Position {
    public boolean isCalcInclude = true;
    private IntSupplier x, y;
    public int w, h;
    private ArrayList<IntSupplier> sx = new ArrayList<>();
    private ArrayList<IntSupplier> sy = new ArrayList<>();
    public Position(IntSupplier x, IntSupplier y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public int getX(){
        return x.getAsInt() + runAddon(sx, 0);
    }
    public int getY(){
        return y.getAsInt() + runAddon(sy, 0);
    }
    public Position setXAddon(IntSupplier s){
        sx.add(s);
        return  this;
    }
    public Position setYAddon(IntSupplier s){
        sy.add(s);
        return  this;
    }
    public boolean isInRect(int mx, int my, LunchScene main){
        int dw = isCalcInclude ? main.DW : 1;
        int dh = isCalcInclude ? main.DH : 1;
        return getX() * dw  <= mx && (getX() + w)  * dw  >= mx &&
                getY() * dh <= my && (getY() + h) * dh >= my;
    }
    private int runAddon(ArrayList<IntSupplier> a, int c){
        return a.size() > c ? a.get(c).getAsInt() + runAddon(a, c + 1) : 0;
    }
}
