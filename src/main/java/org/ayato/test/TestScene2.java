package org.ayato.test;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
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

        Animation.create(scene, Component.get(this, "hello", "apple"),
                Properties.ofText(10, 20).size(64).color(Color.RED)
                        .changeMessage(()->{
                            if(Objects.requireNonNull(Event.get(TestScene2.class, "test")).getEvent()){
                                return Component.get(this, "hello", "Banana");
                            }
                            return null;
                        })
        );

        Animation.create(scene, Component.get(this, "hello", "apple"), Properties.ofText(50, 60)
                .font(new Font("", 0, 32))
                        .color(Color.WHITE)
                        .frame(50, 60, 120, 20, ()->Color.WHITE, Color.GRAY)
                        .talk(this, true, property ->Objects.requireNonNull(Event.get(TestScene2.class, "test")).clear(), "mes1", "mes2")
        );
        Animation.create(scene,"Hello", Properties.ofText(60, 30)
                .font(new Font("", Font.PLAIN, 32))
                .ifView(()-> Objects.requireNonNull(Event.get(TestScene2.class, "test")).getEvent())
        );
        Animation.create(scene,Component.get(this, "valuetest", "Apple", "Orange"),
                Properties.ofText(100, 80).font(new Font("", Font.PLAIN, 32)).color(Color.WHITE)
                        .ifView(()-> Objects.requireNonNull(Event.get(this.getClass(), "test")).getEvent())
        );
        Animation.create(scene, Component.get(this, "nextmap"),
                Properties.ofText(50, 60).font(new Font("", Font.PLAIN, 32))
                        .ifView(()-> Objects.requireNonNull(Event.get(TestScene2.class, "test")).getEvent())
                        .button(50, 60, 120, 20, ()->Color.WHITE,
                                new Color(127, 127, 127, 50), null, property -> scene.changeScene(new TestScene3()))
                        .color(Color.WHITE)

        );
    }
}
