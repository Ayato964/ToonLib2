package org.ayato.system;

import org.ayato.system.properties.IProperty;
import org.ayato.system.properties.Properties;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Supplier;

public class AnimationKeyButtonList<T extends  AnimationList<?>> implements KeyListener {
    T list;
    private boolean visible = false;
    private Color CHOOSE = Color.GRAY;
    private Color NORMAL = Color.WHITE;

    int listCount = 0;

    private AnimationKeyButtonList(T t, int bx, int by, int bw, int bh){
        list = t;
        int mh = bh / list.length();
        
        for(int i = 0; i < list.length(); i ++){

            final AnimationList<?> l = list.get(i);

            l.setup(bx, by + mh * i, l.properties
                            .ifView(()->visible)
                    .frame(bx, by + mh * i, bw, mh,
                            ()->listCount == l.id ? CHOOSE : NORMAL,
                            new Color(127, 127, 127, 50)
                            ));
        }

    }
    public static <L extends AnimationList<?>> AnimationKeyButtonList<L> generate(L l, int bx, int by, int bw, int bh){
        return new AnimationKeyButtonList<>(l, bx, by, bw, bh);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> listCount = listCount == 0 ? list.length() - 1 : listCount - 1;
            case KeyEvent.VK_DOWN -> listCount = listCount < list.length() - 1 ? listCount + 1 : 0;
            case KeyEvent.VK_ENTER -> list.get(listCount).action.action(null);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setVisible(boolean b) {
        if(b)
            list.scene.FRAME.addKeyListener(this);
        else
            list.scene.FRAME.removeKeyListener(this);
        visible = b;
    }
}
