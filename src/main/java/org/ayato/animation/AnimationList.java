package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.LunchScene;
import org.ayato.util.VoidSupplier;

import java.awt.*;
import java.util.function.Consumer;

public class AnimationList<T, M extends Properties> {
    AnimationList<T, M> nextNode;
    private final M properties;
    private AObject<T> object;
    public final LunchScene MASTER;
    private Consumer<AbstractAnimations<?, ?>> drawAction;
    private AbstractAnimations<?, ?> key;
    public AnimationList(LunchScene master, M prop){
        this(master, null, prop, null);
    }

    private AnimationList(LunchScene master, AObject<T> n, M prop, Consumer<AbstractAnimations<?, ?>> action){
        MASTER = master;
        object = n;
        properties = prop;
        drawAction = action;
    }

    public AbstractAnimations<?, ?> getKey() {
        return key;
    }

    public void setKey(AbstractAnimations<?, ?> key) {
        this.key = key;
    }


    public AnimationList<T, M> next(){
        return nextNode;
    }
    public AnimationList<T, M> get(int i){
        return i <= 0 ? this : nextNode.get(i - 1);
    }

    public int length() {
        return nextNode == null ? 1 : 1 + nextNode.length();
    }

    public AObject<T> getNode() {
        return object;
    }

    public M getProperties() {
        return properties;
    }
    public void drawAction(){
        drawAction.accept(key);
    }


}
