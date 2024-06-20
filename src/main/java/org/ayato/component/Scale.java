package org.ayato.component;

import org.ayato.system.ToonMaster;

public class Scale {
    private int w, h;
    public Scale(){
        this(0, 0);
    }
    public Scale(int w, int h){
        this.w = w;
        this.h = h;
    }

    public int getH() {
        return h * ToonMaster.getINSTANCE().DH;
    }

    public int getW() {
        return w * ToonMaster.getINSTANCE().DW;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setW(int w) {
        this.w = w;
    }

}
