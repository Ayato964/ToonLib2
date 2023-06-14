package org.ayato.util;

import java.util.Random;

public interface IBaseScene extends Setup, Display{
    public int SerialID = new Random().nextInt(100000, 10000000);
    public default IBaseScene getInstance(){
        return this;
    }
}
