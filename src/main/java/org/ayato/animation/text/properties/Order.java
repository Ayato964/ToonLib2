package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.AnimationSequentialOrder;
import org.ayato.animation.Properties;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Order implements IProperty{
    private final long max_timer;
    private boolean isFirst = true;
    private StringBuilder baseMessage, arrangeMessage;
    private int messageCount = 0;
    private final AnimationSequentialOrder ORDER;
    public Order(long time){
        this(null, time);
    }
    public Order(AnimationSequentialOrder order, long time){
        max_timer = time;
        ORDER = order;
    }
    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
            Animation<String> a = (Animation<String>) animation;
            if(isFirst){
                baseMessage = new StringBuilder().append(a.getViewObject());
                arrangeMessage = new StringBuilder();
                messageCount = 0;
                Thread t = new Thread(()->count(a));
                t.start();
                isFirst = false;
            }
    }
    protected void count(Animation<String> a){
        while (messageCount < baseMessage.length()) {
            try {
                Thread.sleep(max_timer * 1000 / baseMessage.length());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            arrangeMessage.append(baseMessage.charAt(messageCount));
            a.setViewObject(arrangeMessage.toString());
            messageCount++;

        }
        if (ORDER != null)
            endAnimation(ORDER);
    }

    @Override
    public void reset(int nx, int ny) {
        isFirst = true;
    }
}
