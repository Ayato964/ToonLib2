package org.ayato.util;

import java.util.function.Supplier;

public class RegistoryObject<T> {
    private final Supplier<T> sup;
    private  final  String myID;
    private final String MASTER_ID;

    private RegistoryObject(Supplier<T> sup, String myID, String masterId) {
        this.sup = sup;
        this.myID = myID;
        MASTER_ID = masterId;
    }
    public static <F> RegistoryObject<F> register(Supplier<F> f, String mid, String  master){
        return new RegistoryObject<>(f, mid, master);
    }

    public T get(){
        return sup.get();
    }

    public String getMyID() {
        return myID;
    }

    public String getMASTER_ID() {
        return MASTER_ID;
    }
}
