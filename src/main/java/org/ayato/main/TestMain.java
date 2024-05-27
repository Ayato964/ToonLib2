package org.ayato.main;

import org.ayato.system.LunchScene;
import org.ayato.system.LangLoader;
import org.ayato.test.NewAnimationTest;

public class TestMain {
    public static LunchScene MASTER1;
    public static void main(String[] args) {
        LangLoader.create("assets/ayato/lang", LangLoader.ENGLISH);
        MASTER1 = new LunchScene("TOON_LIB_TEST", true);
        MASTER1.setVisible(true);
        MASTER1.changeScene(new NewAnimationTest());
    }
}
