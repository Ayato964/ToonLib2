package org.ayato.main;

import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.system.LangLoader;
import org.ayato.test.NewAnimationTest;

import java.util.function.Consumer;

public class TestMain {
    public static LunchScene MASTER1;
    public static void main(String[] args) {
        LangLoader.create("assets/ayato/lang", LangLoader.ENGLISH);
        MASTER1 = new LunchScene("TOON_LIB_TEST", true);
        MASTER1.setVisible(true);

        System.out.println(MASTER1.FONT_BASE_SIZE);

        MASTER1.changeScene(new NewAnimationTest());
    }
}
