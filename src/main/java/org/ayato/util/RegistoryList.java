package org.ayato.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

public class RegistoryList<T> {
    private final HashMap<String, RegistoryObject<T>> hash = new HashMap<>();
    private final String ID;
    private RegistoryList(String id){
        ID = id;
    }

    public static <N> RegistoryList<N> create(String id){
        return new RegistoryList<>(id);
    }
    public RegistoryObject<T> create(Supplier<T> sup, String id){
        RegistoryObject<T> ro = RegistoryObject.register(sup, id, ID);
        hash.put(id, ro);
        return ro;
    }
    public RegistoryObject<T> get(String id){
        return hash.get(id);
    }
}
