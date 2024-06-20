package org.ayato.component;

import org.ayato.system.ToonMaster;

import java.util.ArrayList;
import java.util.function.IntSupplier;

public class Position {
    public boolean isCalcInclude = true;
    private int x, y;
    private ArrayList<IntSupplier> sx = new ArrayList<>();
    private ArrayList<IntSupplier> sy = new ArrayList<>();
    private ToonMaster master;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
        master = ToonMaster.getINSTANCE();
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
    @Deprecated
    public Position setXAddon(IntSupplier s){
        sx.add(s);
        return  this;
    }
    @Deprecated
    public Position setYAddon(IntSupplier s){
        sy.add(s);
        return  this;
    }
    public Position addAddon(Position p){
        sx.add(p::getPrefabX);
        sy.add(p::getPrefabY);
        return this;
    }

    private int runAddon(ArrayList<IntSupplier> a, int c){
        if(!a.isEmpty())
            return a.size() > c ? a.get(c).getAsInt() + runAddon(a, c + 1) : 0;
        else
            return 0;
    }
}
