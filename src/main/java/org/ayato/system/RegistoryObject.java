package org.ayato.system;

public class RegistoryObject<T> {
    private final T node;
    private final String ID;
    private RegistoryObject(T t, String id){
        node = t;
        ID = id;
    }

    public static <A> RegistoryObject<A> create(A a, String id){
        return new RegistoryObject<>(a, id);
    }

    public String getID() {
        return ID;
    }
    public T get(){
        return node;
    }
}
