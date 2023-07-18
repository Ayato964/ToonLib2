package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.LunchScene;
import org.ayato.util.VoidSupplier;

import java.awt.*;

public class AnimationList<T, M extends Properties<T>> {
    AnimationList<T, M> nextNode;
    private final M properties;
    private final T object;
    public final LunchScene MASTER;
    private final VoidSupplier drawAction;
    public AnimationList(LunchScene master, T n, M prop, VoidSupplier action){
        MASTER = master;
        object = n;
        properties = prop;
        drawAction = action;
    }
    public void add(T object, VoidSupplier action){
        add(object, properties.copy(), action);
    }
    public void add(T object, M m, VoidSupplier action) {
        if (nextNode != null)
            nextNode.add(object, m, action);
        else
            nextNode = new AnimationList<>(MASTER, object, m, action);
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

    public T getNode() {
        return object;
    }

    public M getProperties() {
        return properties;
    }
    public void drawAction(){
        drawAction.action();
    }
}
