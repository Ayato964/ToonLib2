package org.ayato.system;

public class Component {
    public static String get(Object o, String key){
        return get(o, key, (String) null);
    }
     public static String get(Object o, String key, String... values){
        StringBuilder objKey = new StringBuilder().append(o.getClass().toString()).append(".");
        objKey.delete(0, 6);
        String str = LangLoader.getInstance().get(values, objKey + key);
        if(str == null) {
            create(objKey + key," ");
            return objKey + key;
        }else
            return str;

    }
    public static String getForGlobal(String key, String ... value){
        String str = LangLoader.getInstance().get(value, key);
        if(str != null)
            return str;
        create(key, key);
        return key;
    }
    public static void create(String key, String mes){
        LangLoader.getInstance().createLanguage(key, mes);
    }
}
