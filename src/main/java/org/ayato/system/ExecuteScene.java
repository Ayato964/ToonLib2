package org.ayato.system;

import org.ayato.util.IBaseScene;

import java.awt.*;

public class ExecuteScene {
    public  final DisplayThread SCENE;
    public  final DisplayThread ANIMATION;
    public  final MyFrame       FRAME;
    public final Graphics       GRAPHIC;
    public final Background     BACKGROUND;
    public IBaseScene MY_SCENE;
    public ExecuteScene(String title){
        FRAME = new MyFrame(title);
        GRAPHIC = FRAME.g;
        BACKGROUND = new Background(this);
        SCENE = DisplayThread.runThread(null, this);
        ANIMATION  = DisplayThread.runThread(null, this);
        SCENE.addEndTask(()->SCENE.addDisplay(BACKGROUND));
    }
    public void changeScene(IBaseScene scene){
        if(MY_SCENE == null) {
            SCENE.addEndTask(()->{
                scene.setup(this);
                SCENE.addDisplay(scene);
                MY_SCENE = scene;
            });

        }else{
            SCENE.addEndTask(()->{
                SCENE.removeDisplay(MY_SCENE);
                SCENE.removeDisplayClass(AnimationText.class);
                scene.setup(this);
                SCENE.addDisplay(scene);
                MY_SCENE = scene;
            });
        }
    }

    public void setVisible(boolean b) {
        FRAME.setVisible(b);
    }
}
