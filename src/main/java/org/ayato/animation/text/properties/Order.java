package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.AnimationSequentialOrder;
import org.ayato.animation.Properties;
import org.ayato.animation.TimerPropertyForText;

import java.awt.*;

public class Order extends TimerPropertyForText {
    private StringBuilder baseMessage, arrangeMessage;
    private int messageCount = 0;
    public Order(long time){
        this(null, time);
    }
    public Order(AnimationSequentialOrder order, long time){
       super(order, time);
    }

    @Override
    public void setup(Graphics g, Properties properties, Animation<?> animation) {
        baseMessage = new StringBuilder().append(animation.getViewObject());
        arrangeMessage = new StringBuilder();
        super.setup(g, properties, animation);
    }

    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {}

    @Override
    public void reset(int nx, int ny) {}

    @Override
    protected void run(Graphics g, Properties properties, Animation<String> animation) {
        arrangeMessage.append(baseMessage.charAt(messageCount));
        animation.setViewObject(arrangeMessage.toString());
        messageCount++;
    }

    @Override
    protected boolean getLoopRule() {
        return messageCount < baseMessage.length();
    }
}
