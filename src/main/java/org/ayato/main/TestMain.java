package org.ayato.main;

import org.ayato.system.ExecuteScene;
import org.ayato.system.LangLoader;
import org.ayato.test.TestScene;

public class TestMain {
    public static ExecuteScene MASTER1;
    public static void main(String[] args) {
        LangLoader.create("assets/ayato/lang", LangLoader.JAPANESE);
        System.out.println(LangLoader.getInstance().get(null, "hello"));
        MASTER1 = new ExecuteScene("TOON_LIB_TEST");
        MASTER1.setVisible(true);
        System.out.println("Hello");
        MASTER1.changeScene(new TestScene());
    }
}
