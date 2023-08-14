package org.ayato.test;

import org.ayato.animation.Animation;
import org.ayato.animation.AnimationComponent;
import org.ayato.animation.Properties;
import org.ayato.animation.PropertiesComponent;
import org.ayato.main.TestMain;
import org.ayato.system.Component;
import org.ayato.system.RegistoryList;
import org.ayato.system.RegistoryObject;

import java.awt.*;

public class AnimationFactory {
    private static final AnimationFactory THIS = new AnimationFactory();
    private AnimationFactory(){}
    public static final RegistoryList<Animation<String>> CONV =
            RegistoryList.create(TestMain.MASTER1, "test");

    public static final RegistoryObject<Animation<String>> CONV1 =
            CONV.create(()-> Animation.create(TestMain.MASTER1, AnimationComponent.ofText(Component.get(THIS, "c1")),
                            PropertiesComponent.ofText(20, 20).color(Color.WHITE).size(40), false),
                    "c1");
    public static final RegistoryObject<Animation<String>> CONV2 =
            CONV.create(()->Animation.create(TestMain.MASTER1, AnimationComponent.ofText(""),
                    PropertiesComponent.ofText(100, 100).talk(THIS, true, property -> System.out.println("SSSS"),
                            ()->Component.get(THIS, "1"),()->Component.get(THIS, "2"),
                            ()->Component.get(THIS, "3"),()->Component.get(THIS, "4")),
                    false
            ), "c2");

}
