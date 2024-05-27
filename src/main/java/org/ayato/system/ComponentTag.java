package org.ayato.system;

import java.util.ArrayList;
import java.util.Arrays;

public interface ComponentTag{

    public default void addTag(String... tag){
        getTagsList().addAll(Arrays.asList(tag));
    }
    public default boolean isFindTags(String... f_tags){
        int i = 0;
        for(String tag : f_tags){
            l :for(String my_tag : getTagsList()){
                if(tag.equals(my_tag)){
                    i ++;
                    break l;
                }
            }
        }
        return i == f_tags.length;
    }

    ArrayList<String> getTagsList();
}
