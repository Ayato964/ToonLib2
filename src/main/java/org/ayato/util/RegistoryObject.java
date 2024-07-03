package org.ayato.util;

import java.util.function.Supplier;

public class RegistoryObject<T, V extends Supplier<T>>{
    private final V sup;
    private  final  String myID;
    private final String MASTER_ID;

    private RegistoryObject(V sup, String myID, String masterId) {
        this.sup = sup;
        this.myID = myID;
        MASTER_ID = masterId;
    }
    public static <F, V extends Supplier<F>> RegistoryObject<F, V> register(V f, String mid, String  master){
        return new RegistoryObject<>(f, mid, master);
    }

    public T getInit(){
        return sup.get();
    }
    public V getSup(){
        return sup;
    }

    public String getMyID() {
        return myID;
    }

    public String getMASTER_ID() {
        return MASTER_ID;
    }
}
