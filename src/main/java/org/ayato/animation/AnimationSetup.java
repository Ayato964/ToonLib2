package org.ayato.animation;

import org.ayato.system.LunchScene;

public interface AnimationSetup<T> {
    public void accept(LunchScene scene, T t, int x, int y, Properties<T> p);
}
