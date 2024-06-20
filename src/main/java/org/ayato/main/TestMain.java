package org.ayato.main;

import org.ayato.system.ToonMaster;
import org.ayato.system.LangLoader;
import org.ayato.test.NewAnimationTest;

public class TestMain {
    public static ToonMaster MASTER1;
    public static void main(String[] args) {
        LangLoader.create("assets/ayato/lang", LangLoader.ENGLISH);
        MASTER1 = ToonMaster.create("TOON_LIB_TEST", true);
        MASTER1.setVisible(true);
        MASTER1.changeScene(new NewAnimationTest());
    }
}
