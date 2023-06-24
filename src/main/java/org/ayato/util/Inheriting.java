package org.ayato.util;

import java.util.HashMap;

public class Inheriting {
    private final HashMap<String, Integer> integerKey = new HashMap<>();
    private final HashMap<String, String> stringKey = new HashMap<>();
    private final HashMap<String, Boolean> boolKey = new HashMap<>();
    public  Inheriting(){}

    public void put(String s, int v){
        integerKey.put(s, v);
    }
    public void put(String s, String v){
        stringKey.put(s, v);
    }
    public void put(String s, boolean v){
        boolKey.put(s, v);
    }
    public int getInteger(String s){
        return integerKey.get(s);
    }

    public String getString(String s){
        return stringKey.get(s);
    }
    public Boolean getBoolean(String s){
        return boolKey.get(s);
    }

}
