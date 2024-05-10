package org.ayato.system;

import org.ayato.animation.Properties;
import org.ayato.animation.TextProperties;
import org.ayato.animation.module.InputModule;
import org.ayato.animation.text.properties.PropertyAction;

import java.util.function.Consumer;

public class AnimationHandler {
    private final LunchScene SCENE;
    public AnimationHandler(LunchScene scene){
        SCENE = scene;
    }
    public InputModule createInputModule(String actionText, Consumer<String> pressEnter, TextProperties prop){
        InputModule i = new InputModule(SCENE, actionText, prop, pressEnter);
        return i;
    }
    public InputModule addInputModule(String actionText, Consumer<String> pressEnter, TextProperties prop){
        InputModule i = new InputModule(SCENE, actionText, prop, pressEnter);
        SCENE.addAnimation(i);
        return i;
    }

}
