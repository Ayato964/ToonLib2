package org.ayato.util;

import org.ayato.system.ComponentGroup;
import org.ayato.system.ComponentTag;
import org.ayato.system.Serial;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {
    public static <T extends Serial> boolean isFindObject(T key, List<T> array){
        for(T t : array){
            if(t.getSerialID() == key.getSerialID())
                return true;
        }
        return false;
    }
    public static <T extends ComponentTag> ArrayList<T> searchObjectForTag(List<T> list, String ... tags){
        ArrayList<T> newList = new ArrayList<>();
        for(T t : list){
            if(t.getTagsList() != null)
                if(t.isFindTags(tags))
                    newList.add(t);
        }
        return newList;
    }
    public static <T extends ComponentGroup> ArrayList<T> searchObjectForGroup(List<T> list, String group){
        ArrayList<T> newList = new ArrayList<>();
        for(T t : list){
            if(t.getGroup() != null)
                if(t.isGroup(group))
                    newList.add(t);
        }
        return newList;
    }
    public static <T> ArrayList<T> searchObjectForClass(List<T> list, Class<T> tClass){
        ArrayList<T> newList = new ArrayList<>();

        for(T t : list){
            if(tClass.isInstance(t))
                newList.add(t);
        }

        return newList;
    }
}
