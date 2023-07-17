package org.ayato.animation;

import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.LunchScene;

public class AnimationList<T, M extends Properties<T>> {
    AnimationList<T, M> nextNode;
    private final M properties;
    private final T object;
    private final LunchScene MASTER;
    private final PropertyAction drawAction;
    public AnimationList(LunchScene master, T n, M prop, PropertyAction action){
        MASTER = master;
        object = n;
        properties = prop;
        drawAction = action;
    }
    public void add(T object, PropertyAction action){
        add(object, properties, action);
    }
    public void add(T object, M m, PropertyAction action) {
        if (nextNode != null)
            nextNode.add(object, m, action);
        else
            nextNode = new AnimationList<>(MASTER, object, m, action);
    }
    public AnimationList<T, M> next(){
        return nextNode;
    }
    public AnimationList<T, M> get(int i){
        return i == 0 ? this : nextNode.get(i - 1);
    }
}
