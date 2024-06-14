package org.ayato.util;

import org.ayato.system.LunchScene;

import java.util.ArrayList;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Position {
    public boolean isCalcInclude = true;
    private int x, y, w, h;
    private ArrayList<IntSupplier> sx = new ArrayList<>();
    private ArrayList<IntSupplier> sy = new ArrayList<>();
    public Position(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public int getX(){
        return x + runAddon(sx, 0);
    }
    public int getY(){
        return y + runAddon(sy, 0);
    }
    public int getNormalX(){
        return x;
    }
    public int getNormalY(){
        return y;
    }
    public Position setX(int dx){
        x = dx;
        return this;
    }
    public Position setY(int dy){
        y = dy;
        return this;
    }

    public Position setW(int w) {
        this.w = w;
        return this;
    }

    public Position setH(int h) {
        this.h = h;
        return this;
    }

    public Position setXAddon(IntSupplier s){
        sx.add(s);
        return  this;
    }
    public Position setYAddon(IntSupplier s){
        sy.add(s);
        return  this;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
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
