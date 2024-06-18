package org.ayato.test;

import org.ayato.animation.Animation;
import org.ayato.animation.MoveTo;
import org.ayato.system.LunchScene;
import org.ayato.util.BaseScene;
import org.ayato.component.Position;
import org.ayato.util.Setup;

import java.awt.*;
import java.util.ArrayList;

public class AnimationScene extends BaseScene {
    private final Position position = new Position(0, 0);

    @Override
    public void display(Graphics g) {
        position.setX(position.getNormalX() + 1);
    }

    @Override
    public void createUI(LunchScene scene) {
        scene.BACKGROUND.mode.setColor(Color.BLACK);
        scene.addAnimation("Move", NewAnimationTest.TEMPLATE.of(10, 100)
                .pushMatrix()
                    .fadeIn(300)
                    .move(50, 50)
                .endMatrix());
        Animation<?> a = scene.addAnimation("MoveTo", NewAnimationTest.TEMPLATE.of(10, 10)
                .pushMatrix()
                .moveTo(300, 10, 100, MoveTo.VelocityFormat.CURVE)
                        .sleep(300)
                        .moveTo(300, 200, 300, MoveTo.VelocityFormat.CONSTANT)
                .endMatrix());

        scene.addAnimation("Wait Test", NewAnimationTest.TEMPLATE.of(10, 20)
                .pushMatrix()
                .wait(()->position.getX()>= scene.FRAME.DESCTOP_BOUNDS.width)
                        .moveTo(40, 40, 200, MoveTo.VelocityFormat.CONSTANT)
                .endMatrix());

        scene.addAnimation("ParentAnimation", NewAnimationTest.TEMPLATE.of(100, 100)
                .parent(a));
        scene.addAnimation("ParentPosition1", NewAnimationTest.TEMPLATE.of(0, 120)
                .parent(position));
        scene.addAnimation("ParentPosition2", NewAnimationTest.TEMPLATE.of(10, 150)
                .parent(position));
    }

    @Override
    public void setupUIClass(ArrayList<Setup> setups) {
        setups.add(new ButtonSetup(ModuleAnimationTest::new, ToonObjectTest::new, "<", ">"));
    }

    @Override
    public void tick() {

    }
}
