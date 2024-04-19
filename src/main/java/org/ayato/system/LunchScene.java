package org.ayato.system;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.animation.image.ImageMaker;
import org.ayato.util.IBaseScene;
import org.ayato.util.KeyInputs;
import org.ayato.util.MouseInputs;

import java.awt.*;

public class LunchScene {
    public  final DisplayThread SCENE;
    public  final DisplayThread ANIMATION;
    public  final MyFrame       FRAME;
    public final Graphics       GRAPHIC;
    public final Background     BACKGROUND;
    public IBaseScene MY_SCENE;
    public LunchScene(String title){
        FRAME = new MyFrame(title);
        GRAPHIC = FRAME.g;
        BACKGROUND = new Background(this);
        SCENE = DisplayThread.runThread(null, this);
        ANIMATION  = DisplayThread.runThread(null, this);
        SCENE.addEndTask(()->SCENE.addDisplay(BACKGROUND));
        KeyInputs.init(this);
        MouseInputs.init(this);
    }
    public LunchScene(String str, int dw, int dh){
        FRAME = new MyFrame(str);
        FRAME.setSize(dw, dh);
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
                SCENE.removeDisplayClass(Animation.class);
                FRAME.removeMouseListenerAll();
                FRAME.removeKeyListenerAll();
                scene.setup(this);
                SCENE.addDisplay(scene);
                MY_SCENE = scene;
            });
        }
    }
    public <A> Animation<A> animation(A object, Properties<A> prop){
        Animation<A> animation = new Animation<>(this, object, prop);
        SCENE.addDisplay(animation);
        animation.init();
        return animation;
    }
    public void setVisible(boolean b) {
        FRAME.setVisible(b);
    }
}
