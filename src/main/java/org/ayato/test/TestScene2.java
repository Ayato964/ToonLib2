package org.ayato.test;

import org.ayato.animation.text.AnimationText;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.animation.text.properties.TextProperties;
import org.ayato.util.Event;
import org.ayato.util.IBaseScene;

import java.awt.*;
import java.util.Objects;

public class TestScene2 implements IBaseScene {
    @Override
    public void display(Graphics g) {


    }
    @Override
    public void setup(LunchScene scene) {
        Event.create(TestScene2.class, "test", null);

        AnimationText.create(scene, Component.get(this, "hello", "apple"), 10, 20,
                new TextProperties().size(64).color(Color.RED)
                        .changeMessage(()->{
                            if(Objects.requireNonNull(Event.get(TestScene2.class, "test")).getEvent()){
                                return Component.get(this, "hello", "Banana");
                            }
                            return null;
                        })
        );

        AnimationText.create(scene, Component.get(this, "hello"), 50, 60, new TextProperties()
                .font(new Font("", 0, 32))
                        .color(Color.WHITE)
                        .frame(50, 60, 120, 20, ()->Color.WHITE, Color.GRAY)
                        .talk(this, true, property ->Objects.requireNonNull(Event.get(TestScene2.class, "test")).clear(), "mes1", "mes2")
        );
        AnimationText.create(scene,"Hello", 60, 30, new TextProperties()
                .font(new Font("", Font.PLAIN, 32))
                .ifView(()-> Objects.requireNonNull(Event.get(TestScene2.class, "test")).getEvent())
        );
        AnimationText.create(scene,Component.get(this, "valuetest", "Apple", "Orange"),
                100,
                80,
                new TextProperties().font(new Font("", Font.PLAIN, 32)).color(Color.WHITE)
                        .ifView(()-> Objects.requireNonNull(Event.get(this.getClass(), "test")).getEvent())
        );
        AnimationText.create(scene, Component.get(this, "nextmap"), 50, 60,
                new TextProperties().font(new Font("", Font.PLAIN, 32))
                        .ifView(()-> Objects.requireNonNull(Event.get(TestScene2.class, "test")).getEvent())
                        .button(50, 60, 120, 20, ()->Color.WHITE,
                                new Color(127, 127, 127, 50), null, property -> scene.changeScene(new TestScene3()))
                        .color(Color.WHITE)

        );
    }
}
