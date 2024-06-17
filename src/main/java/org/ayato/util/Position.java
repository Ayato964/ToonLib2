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
    private LunchScene master;
    public Position(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        master = LunchScene.getINSTANCE();
    }
    public int getX(){
        return (x + runAddon(sx, 0)) * master.DW;
    }
    public int getY(){
        return (y + runAddon(sy, 0)) * master.DH;
    }
    public int getPrefabX(){
        return x + runAddon(sx, 0);
    }
    public int getPrefabY(){
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
        return w * master.DW;
    }

    public int getH() {
        return h * master.DH;
    }

    public boolean isInRect(int mx, int my){
        return getX()  <= mx && (getX() + getW()) >= mx &&
                getY() <= my && (getY() + getH()) >= my;
    }
    private int runAddon(ArrayList<IntSupplier> a, int c){
        if(!a.isEmpty())
            return a.size() > c ? a.get(c).getAsInt() + runAddon(a, c + 1) : 0;
        else
            return 0;
    }
}
