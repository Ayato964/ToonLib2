package org.ayato.system;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.util.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

/**
 * ToonMaster is master class of ToonLib.
 * It's singleton class.<br>
 * At first. please run the {@code create()} method.<br>
 * At second. Please create class to extends {@code BaseScene} class.<br>
 * At third. Please add UI and Toon2DObject as needed.<br>
 * And finally. Please enjoy coding with ToonLib2!
 * @author Ayato
 * @see ToonMaster#create(String, boolean)
 * @see ToonMaster#create(String, int, int)
 * @see BaseScene
 * @see Animation
 * @see org.ayato.component.ToonObject
 *
 */
public final class ToonMaster {
    /**
     *  SCENE manages objects that inherit from the {@code Display} interface.<br>
     * @see DisplayThread
     * @see Animation
     * @see org.ayato.component.ToonObject
     * @see BaseScene
     * @apiNote 0.1.0
     */
    public  final DisplayThread SCENE;


    public  final TickThread TICK;
    public  final MyFrame       FRAME;
    public final Graphics       GRAPHIC;
    public final AnimationHandler HANDLER;
    public final Background     BACKGROUND;
    public BaseScene MY_SCENE;
    public int DW;
    public int DH;

    public final int FONT_BASE_SIZE;

    private static ToonMaster INSTANCE;
    private ToonMaster(String title, boolean isFull){
        FRAME = new MyFrame(title, isFull);
        DW = (int) FRAME.DESCTOP_BOUNDS.getWidth() / 400;
        DH =(int) FRAME.DESCTOP_BOUNDS.getHeight() / 200;
        FONT_BASE_SIZE = (DW * 5 + DH * 5) / 2;

        GRAPHIC = FRAME.g;
        BACKGROUND = new Background(this);
        SCENE = DisplayThread.runThread(null, this);
        HANDLER = new AnimationHandler(this);
        KeyInputs.init(this);
        MouseInputs.init(this);
        TimeCounter.init();
        TICK = TickThread.INSTANCE;
    }
    private ToonMaster(String str, int dw, int dh){
        FRAME = new MyFrame(str, false);
        FRAME.setSize(dw, dh);
        DW = (int) FRAME.DESCTOP_BOUNDS.getWidth() / 400;
        DH =(int) FRAME.DESCTOP_BOUNDS.getHeight() / 400;
        FONT_BASE_SIZE = (DW * 5 + DH * 5) / 2;


        GRAPHIC = FRAME.g;
        BACKGROUND = new Background(this);
        SCENE = DisplayThread.runThread(null, this);
        HANDLER = new AnimationHandler(this);
        KeyInputs.init(this);
        MouseInputs.init(this);
        TimeCounter.init();
        TICK = TickThread.INSTANCE;
    }

    /**
     * This method can create {@code ToonMaster} class only one.
     * @see ToonMaster
     * @param title Enter your game name.
     * @param isFull Do you want fullscreen
     * @return This class.
     */
    public static ToonMaster create(String title, boolean isFull){
        if(INSTANCE == null)
            INSTANCE = new ToonMaster(title, isFull);
        return INSTANCE;
    }

    /**
     * This method can create {@code ToonMaster} class only one.
     * @param title Enter your game name.
     * @param w frame width
     * @param h frame height
     * @return this class
     * @see ToonMaster
     */
    public static ToonMaster create(String title, int w, int h){
        if(INSTANCE != null)
            INSTANCE = new ToonMaster(title, w, h);
        return INSTANCE;
    }

    public static ToonMaster getINSTANCE() {
        return INSTANCE;
    }

    public void changeScene(BaseScene scene){
        if(MY_SCENE == null) {
            SCENE.addEndTask(()->{
                SCENE.addDisplay(BACKGROUND);
                scene.runSetupClass(this);
                scene.runDisplayClass(SCENE);
                scene.runTickClass(TICK);
                scene.setToonObjectClass(this);
                TICK.add(scene);
                MY_SCENE = scene;
            });

        }else{
            SCENE.addEndTask(()->{
                SCENE.removeDisplayAll();
                TICK.removeAll();
                SCENE.addDisplay(BACKGROUND);
                MouseInputs.removeAll();
                KeyInputs.removeAll();
                TimeCounter.removeAll();

                scene.runSetupClass(this);
                scene.runDisplayClass(SCENE);
                scene.runTickClass(TICK);
                scene.setToonObjectClass(this);
                MY_SCENE = scene;
            });
        }
    }
    public <A> Animation<A> addAnimation(Supplier<A> object, Properties<A> prop){
        Animation<A> animation = new Animation<>(this, object, prop);
        SCENE.addDisplay(animation);
        animation.init();
        return animation;
    }
    public <A> Animation<A> createAnimation(Supplier<A> object, Properties<A> prop){
        return new Animation<>(this, object, prop);
    }
    public <A> Animation<A> addAnimation(A object, Properties<A> prop){
        Animation<A> animation = new Animation<>(this, ()->object, prop);
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
        return new Animation<>(this, ()->object, prop);
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
