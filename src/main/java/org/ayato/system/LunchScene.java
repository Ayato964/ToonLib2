package org.ayato.system;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.animation.image.ImageMaker;
import org.ayato.util.Display;
import org.ayato.util.IBaseScene;
import org.ayato.util.KeyInputs;
import org.ayato.util.MouseInputs;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public final class LunchScene {
    public  final DisplayThread SCENE;
    public  final DisplayThread ANIMATION;
    public  final MyFrame       FRAME;
    public final Graphics       GRAPHIC;
    public final AnimationHandler HANDLER;
    public final Background     BACKGROUND;
    public IBaseScene MY_SCENE;
    public int DW;
    public int DH;

    public final int FONT_BASE_SIZE;

    public LunchScene(String title, boolean isFull){
        FRAME = new MyFrame(title, isFull);
        DW = (int) FRAME.DESCTOP_BOUNDS.getWidth() / 400;
        DH =(int) FRAME.DESCTOP_BOUNDS.getHeight() / 200;
        FONT_BASE_SIZE = (DW * 5 + DH * 5) / 2;

        GRAPHIC = FRAME.g;
        BACKGROUND = new Background(this);
        SCENE = DisplayThread.runThread(null, this);
        ANIMATION  = DisplayThread.runThread(null, this);
        HANDLER = new AnimationHandler(this);
        SCENE.addEndTask(()->SCENE.addDisplay(BACKGROUND));
        KeyInputs.init(this);
        MouseInputs.init(this);
    }
    public LunchScene(String str, int dw, int dh){
        FRAME = new MyFrame(str, false);
        FRAME.setSize(dw, dh);
        DW = (int) FRAME.DESCTOP_BOUNDS.getWidth() / 400;
        DH =(int) FRAME.DESCTOP_BOUNDS.getHeight() / 200;
        FONT_BASE_SIZE = (DW * 5 + DH * 5) / 2;


        GRAPHIC = FRAME.g;
        BACKGROUND = new Background(this);
        SCENE = DisplayThread.runThread(null, this);
        ANIMATION  = DisplayThread.runThread(null, this);
        HANDLER = new AnimationHandler(this);
        SCENE.addEndTask(()->SCENE.addDisplay(BACKGROUND));
        KeyInputs.init(this);
        MouseInputs.init(this);
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
    public <A> Animation<A> addAnimation(A object, Properties<A> prop){
        Animation<A> animation = new Animation<>(this, object, prop);
        SCENE.addDisplay(animation);
        animation.init();
        return animation;
    }
    public <A> Animation<A> addAnimation(Animation<A> animation){
        SCENE.addDisplay(animation);
        animation.init();
        return animation;
    }
    public <A> Animation<A> createAnimation(A object, Properties<A> prop){
        return new Animation<>(this, object, prop);
    }

    public void setVisible(boolean b) {
        FRAME.setVisible(b);
    }
    public Font getMakeFont(String fontName, int style, float size){
        return new Font(fontName, style, (int) (FONT_BASE_SIZE * size));
    }
    public <T> ArrayList<T> getComponentsClass(Class<T> serch){
        ArrayList<T> arrays = new ArrayList<>();
        CopyOnWriteArrayList<Display> dis = SCENE.getDisplay();
        for(Display d : dis){
            if(serch.isInstance(d)){
                arrays.add((T) d);
            }
        }
        return arrays;
    }
    public ArrayList<Display> getComponentsTag(String... tag){
        ArrayList<Display> correct = new ArrayList<>();
        ArrayList<ComponentTag> tags = SCENE.getTagComponents();
        for(ComponentTag t : tags){
            if(t.isFindTags(tag)){
                correct.add((Display) t);
            }
        }
        return correct;
    }
    public ArrayList<Display> getComponentsGroupID(String id){
        ArrayList<Display> correct = new ArrayList<>();
        ArrayList<ComponentGroup> groups = SCENE.getGroupComponent();
        for(ComponentGroup g : groups){
            if(g.getGroup() != null)
                if(g.isGroup(id))
                    correct.add((Display) g);
        }
        return correct;
    }
}
