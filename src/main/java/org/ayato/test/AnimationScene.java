package org.ayato.test;

import org.ayato.animation.MoveTo;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;
import org.ayato.util.Setup;

import java.awt.*;
import java.util.ArrayList;

public class AnimationScene implements IBaseScene {

    @Override
    public void display(Graphics g) {

    }

    @Override
    public void setup(LunchScene scene) {
        scene.BACKGROUND.mode.setColor(Color.BLACK);
        scene.addAnimation("Move", NewAnimationTest.TEMPLATE.of(10, 100)
                .pushMatrix()
                    .fadeIn(300)
                    .move(50, 50)
                .endMatrix());
        scene.addAnimation("MoveTo", NewAnimationTest.TEMPLATE.of(10, 10)
                .pushMatrix()
                .moveTo(300, 10, 300, MoveTo.VelocityFormat.CURVE)
                        .moveTo(300, 200, 300, MoveTo.VelocityFormat.CONSTANT)
                .endMatrix());
    }

    @Override
    public void setupClass(ArrayList<Setup> setups) {
        setups.add(new ButtonSetup(ModuleAnimationTest::new, null, "<", ">"));
    }
}
