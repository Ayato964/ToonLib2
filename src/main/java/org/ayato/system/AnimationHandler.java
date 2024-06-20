package org.ayato.system;

import org.ayato.animation.TextProperties;
import org.ayato.animation.module.InputModule;

import java.util.function.Consumer;

public class AnimationHandler {
    private final ToonMaster SCENE;
    public AnimationHandler(ToonMaster scene){
        SCENE = scene;
    }
    public InputModule createInputModule(String actionText, Consumer<String> pressEnter,int w, int h, TextProperties prop){
        InputModule i = new InputModule(SCENE, ()->actionText, prop,w, h, pressEnter);
        return i;
    }
    public InputModule addInputModule(String actionText, Consumer<String> pressEnter, int w, int h, TextProperties prop){
        InputModule i = new InputModule(SCENE, ()->actionText, prop, w, h, pressEnter);
        SCENE.addAnimation(i);
        return i;
    }
    /*
    public <T> ChangeObjectModule<T> addChangeObjectModule(BiConsumer<ChangeObjectModule<T>, T[]> condition, Properties<T> prop, T... changeObj){
        ChangeObjectModule<T> co = new ChangeObjectModule<>(SCENE, condition, prop, changeObj);
        SCENE.addAnimation(co);
        return co;
    }

     */

}
