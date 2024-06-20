package org.ayato.animation;

import org.ayato.system.ToonMaster;

public interface AnimationSetup<T> {
    public void accept(ToonMaster scene, T t, int x, int y, Properties p);
}
