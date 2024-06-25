package org.ayato.component;

import org.ayato.system.ToonMaster;

import java.util.ArrayList;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Position {
    public boolean isCalcInclude = true;
    private float x, y;
    private ArrayList<Supplier<Float>> sx = new ArrayList<>();
    private ArrayList<Supplier<Float>> sy = new ArrayList<>();
    private ToonMaster master;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
        master = ToonMaster.getINSTANCE();
    }
    public int getX(){
        return (int) ((x + runAddon(sx, 0)) * master.DW);
    }
    public int getY(){
        return (int) ((y + runAddon(sy, 0)) * master.DH);
    }
    public float getPrefabX(){
        return (int) (x + runAddon(sx, 0));
    }
    public float getPrefabY(){
        return (int) (y + runAddon(sy, 0));
    }
    public float getNormalX(){
        return x;
    }
    public float getNormalY(){
        return y;
    }
    public Position setX(float dx){
        x = dx;
        return this;
    }
    public Position setY(float dy){
        y = dy;
        return this;
    }
    public Position addX( int dx){
        x += dx;
        return this;
    }
    public Position addY( int dy){
        y += dy;
        return this;
    }
    @Deprecated
    public Position setXAddon(Supplier<Float> s){
        sx.add(s);
        return  this;
    }
    @Deprecated
    public Position setYAddon(Supplier<Float> s){
        sy.add(s);
        return  this;
    }
    public Position addAddon(Position p){
        sx.add(p::getPrefabX);
        sy.add(p::getPrefabY);
        return this;
    }

    private float runAddon(ArrayList<Supplier<Float>> a, int c){
        if(!a.isEmpty())
            return a.size() > c ? a.get(c).get() + runAddon(a, c + 1) : 0;
        else
            return 0;
    }
}
