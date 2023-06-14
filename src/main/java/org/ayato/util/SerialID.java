package org.ayato.util;

import java.util.Random;

public interface SerialID {
    public  int SerialID = new Random().nextInt(100000, 10000000);
    default int getSerialID(){
        return SerialID;
    }
}
