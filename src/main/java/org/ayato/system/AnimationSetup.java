package org.ayato.system;

import org.ayato.system.properties.Properties;

public interface AnimationSetup<T> {
    public void accept(ExecuteScene scene, T t, int x, int y, Properties p);
}
