package org.ayato.test;

import org.ayato.animation.AnimationState;
import org.ayato.animation.PropertiesComponent;
import org.ayato.animation.TextProperties;
import org.ayato.system.LunchScene;
import org.ayato.util.BaseScene;
import org.ayato.util.PropertiesSupplier;
import org.ayato.util.Setup;

import java.awt.*;
import java.util.function.Supplier;

public class ButtonSetup implements Setup {
    public static final Supplier<AnimationState> STATE =()-> new AnimationState(
            Color.WHITE,
            Color.BLUE,
            Color.RED,
            new Color(10, 30, 70, 80),
            new Color(10, 30, 70, 80),
            new Color(10, 30, 70, 80)
    );
    public static final PropertiesSupplier<TextProperties> TEMPLATE =(x, y) -> PropertiesComponent.ofText(x, y)
            .color(Color.WHITE)
            .font("", Font.PLAIN, 1.5f);
    private final Supplier<BaseScene> BACK;
    private final Supplier<BaseScene> NEXT;
    private final String b, n;
    public ButtonSetup(Supplier<BaseScene> back, Supplier<BaseScene> next, String b, String n){
        BACK = back;
        NEXT = next;
        this.b = b;
        this.n = n;
    }
    @Override
    public void createUI(LunchScene scene) {
        if(BACK != null)
            scene.addAnimation(b, TEMPLATE.of(10, 180)
                .button(0, 0, 20, 20, STATE.get(), a->scene.changeScene(BACK.get())));
        if(NEXT != null)
            scene.addAnimation(n, TEMPLATE.of(450, 180)
                .button(0, 0, 20, 20, STATE.get(), a->scene.changeScene(NEXT.get())));
    }
}
