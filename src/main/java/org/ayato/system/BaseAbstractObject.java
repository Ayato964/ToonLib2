package org.ayato.system;

import org.ayato.util.Display;

import java.util.ArrayList;
import java.util.Random;

public abstract class BaseAbstractObject implements ComponentTag, ComponentGroup, Display {
    private final long serial = new Random().nextLong(0, 1000000);
    private final ArrayList<String> tags = new ArrayList<>();
    private String groupName;
    @Override
    public ArrayList<String> getTagsList() {
        return tags;
    }

    @Override
    public ComponentGroup setGroup(String str) {
        groupName = str;
        return this;
    }

    @Override
    public String getGroup() {
        return groupName;
    }

    @Override
    public long getSerialID() {
        return serial;
    }
}
