package org.ayato.main;

import org.ayato.system.ExecuteScene;
import org.ayato.test.TestScene;

public class TestMain {
    public static ExecuteScene MASTER1;
    public static void main(String[] args) {
        MASTER1 = new ExecuteScene("TOON_LIB_TEST");
        MASTER1.setVisible(true);
        MASTER1.changeScene(new TestScene());
    }
}
