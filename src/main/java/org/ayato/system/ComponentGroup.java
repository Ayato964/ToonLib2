package org.ayato.system;

public interface ComponentGroup {
    public ComponentGroup setGroup(String str);

    public default boolean isGroup(String ser){
        return getGroup().equals(ser);
    }

    public String getGroup();
}
