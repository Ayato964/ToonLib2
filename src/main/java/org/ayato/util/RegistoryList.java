package org.ayato.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

public class RegistoryList<T, V extends Supplier<T>> {
    private final HashMap<String, RegistoryObject<T, V>> hash = new HashMap<>();
    private final String ID;
    private RegistoryList(String id){
        ID = id;
    }

    public static <N, M extends Supplier<N>> RegistoryList<N, M> create(String id){
        return new RegistoryList<>(id);
    }
    public RegistoryObject<T, V> create(V sup, String id){
        RegistoryObject<T, V> ro = RegistoryObject.register(sup, id, ID);
        hash.put(id, ro);
        return ro;
    }
    public RegistoryObject<T, V> get(String id){
        return hash.get(id);
    }
}
