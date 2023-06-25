package org.ayato.test;

import org.ayato.system.AnimationText;
import org.ayato.system.Component;
import org.ayato.system.ExecuteScene;
import org.ayato.system.properties.Properties;
import org.ayato.util.Event;
import org.ayato.util.IBaseScene;

import java.awt.*;
import java.util.Objects;

public class TestScene2 implements IBaseScene {
    @Override
    public void display(Graphics g) {


    }
    @Override
    public void setup(ExecuteScene scene) {
        Event.create(TestScene2.class, "test", null);

        AnimationText.create(scene).draw(Component.get(this, "hello", "apple"), 10, 20,
                new Properties().size(64).color(Color.RED)
                        .changeMessage(()->{
                            if(Objects.requireNonNull(Event.get(TestScene2.class, "test")).getEvent()){
                                return Component.get(this, "hello", "Banana");
                            }
                            return null;
                        })
        );

        AnimationText.create(scene).draw(Component.get(this, "hello"), 50, 60, new Properties()
                .font(new Font("", 0, 32))
                        .color(Color.WHITE)
                        .frame(50, 60, 120, 20, Color.WHITE, Color.GRAY)
                        .talk(this, true, property ->Objects.requireNonNull(Event.get(TestScene2.class, "test")).clear(), "mes1", "mes2")
        );
        AnimationText.create(scene).draw("Hello", 60, 30, new Properties()
                .font(new Font("", Font.PLAIN, 32))
                .ifView(()-> Objects.requireNonNull(Event.get(TestScene2.class, "test")).getEvent())
        );
        AnimationText.create(scene).draw(Component.get(this, "valuetest", "Apple", "Orange"),
                100,
                80,
                new Properties().font(new Font("", Font.PLAIN, 32)).color(Color.WHITE)
                        .ifView(()-> Objects.requireNonNull(Event.get(this.getClass(), "test")).getEvent())
        );
    }
}
