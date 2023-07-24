package org.ayato.system;

import java.util.ArrayList;
import java.util.function.Supplier;

public class RegistoryList<T> {
    private final ArrayList<RegistoryObject<T>> LIST = new ArrayList<>();
    private final LunchScene MASTER;
    private final String BASE_ID;
    private RegistoryList(LunchScene M, String id){
        MASTER = M;
        BASE_ID = id;
    }
    public static <A> RegistoryList<A> create(LunchScene MASTER, String base_id){
        return new RegistoryList<>(MASTER, base_id);
    }
    public RegistoryObject<T> create(Supplier<T> t, String sub_id){
        String id = BASE_ID + "." +  sub_id;
        RegistoryObject<T> c = RegistoryObject.create(t, id);
        LIST.add(c);
        return c;
    }
    public RegistoryObject<T> get(String id){
        for(RegistoryObject<T> o : LIST)if(o.getID().equals(BASE_ID + "." +  id)) return o;
        return null;
    }
}
