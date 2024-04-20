package org.ayato.animation;

import java.util.ArrayList;

public class AnimationGroup{
    int x, y;

    public AnimationGroup(int x, int y, Animation<?>... animation){
        this.x = x;
        this.y = y;
    }
}
