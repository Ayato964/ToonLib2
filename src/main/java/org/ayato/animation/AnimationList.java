package org.ayato.animation;

import org.ayato.animation.text.properties.TextProperties;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.LunchScene;

import java.util.ArrayList;

public class AnimationList<T>{
    public int id;
    protected final TextProperties properties;
    protected final LunchScene scene;
    private final ArrayList<AnimationList<T>> list;
    private T object;
    public AnimationSetup<T> setup;
    public PropertyAction action;
    private AnimationList(LunchScene executeScene, T str, TextProperties p){
        properties = p;
        this.scene = executeScene;
        object = str;
        list = new ArrayList<>();

    }
    public void add(T str){
        add(str, action);
    }
    public void add(T str, PropertyAction a){
        add(str, properties.copy(), a);
    }

    public void add(T str, TextProperties p, PropertyAction a){
        list.add(create(scene, str, p, setup, a, list.size()));
    }
    public int length(){
        return list.size();
    }

    private static <A> AnimationList<A> create(LunchScene scene, A str, TextProperties p, AnimationSetup<A> s, PropertyAction a, int id){
        AnimationList<A> n = new AnimationList<>(scene, str, p);
        n.list.add(n);
        n.id = id;
        n.setup = s;
        n.action = a;
        return n;
    }

    public static <A> AnimationList<A> create(LunchScene scene, A str, TextProperties p, AnimationSetup<A> s, PropertyAction a){
        AnimationList<A> n = new AnimationList<>(scene, str, p);
        n.list.add(n);
        n.id = 0;
        n.setup = s;
        n.action = a;
        return n;
    }

    public void setup(int bx, int by, TextProperties properties) {
        setup.accept(scene, object, bx, by, properties);
    }

    public T getObject() {
        return object;
    }

    public AnimationList<T> get(int i) {
        return list.get(i);
    }
}
