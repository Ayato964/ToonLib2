package org.ayato.animation;

import org.ayato.system.LunchScene;
import org.ayato.util.Position;

import java.util.ArrayList;
import java.util.List;

public class AnimationGroup{
    public Position position;
    private Animation<?>[] animations;
    public AnimationGroup(int x, int y, Animation<?>... animation){
        position = new Position(()->x, ()->y, 0, 0);
        animations = animation;
        for(Animation<?> a : animation){
            a.properties.position.setXAddon(()->position.getX());
            a.properties.position.setYAddon(()->position.getY());
        }
    }

    public void view(LunchScene scene) {
        for(Animation<?> a : animations)
            scene.addAnimation(a);
    }
}
