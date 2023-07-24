package org.ayato.system;

import java.util.function.Supplier;

public class RegistoryObject<T> {
    private final Supplier<T> node;
    private final String ID;
    private RegistoryObject(Supplier<T> t, String id){
        node = t;
        ID = id;
    }

    public static <A> RegistoryObject<A> create(Supplier<A> a, String id){
        return new RegistoryObject<>(a, id);
    }

    public String getID() {
        return ID;
    }
    public T get(){
        return node.get();
    }
}
