package org.ayato.animation.module;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.system.LunchScene;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ChangeObjectModule<T> extends Animation<T> {

    public ChangeObjectModule(LunchScene scene, BiConsumer<ChangeObjectModule<T>, T[]> condition, Properties<T> prop, T[] changeObj) {
        super(scene, changeObj[0] , prop);
    }
}
