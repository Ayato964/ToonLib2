package org.ayato.system;

import java.util.ArrayList;

public interface ComponentTag {
    ArrayList<String> tags = new ArrayList<>();

    public void setTag(ArrayList<String> myTag);

    public default ArrayList<String> getTags(){
        setTag(tags);
        return tags;
    }
    public default boolean isFindTags(String... f_tags){

        for(String tag : f_tags){

        }
        return true;
    }
}
