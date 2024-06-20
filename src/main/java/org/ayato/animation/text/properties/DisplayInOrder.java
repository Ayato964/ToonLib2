package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.util.ITimeCounter;
import org.ayato.util.TimeCounter;

import java.awt.*;

public class DisplayInOrder implements ITimeCounter, IProperty {
    private int textCount = 0;
    private boolean isEnd = false;
    private String baseText;
    private StringBuilder arrangeText = new StringBuilder();
    public DisplayInOrder(long maxTime) {
        TimeCounter.add(this, maxTime);
    }

    @Override
    public void setupProperty(Graphics2D o2, Graphics g, Properties<?> properties, Animation<?> animation) {
        if(animation.mes instanceof String){
            Animation<String> newAnimation = (Animation<String>) animation;
            baseText = newAnimation.mes;
            newAnimation.setViewObject("");
        }
    }

    @Override
    public void timeTick() {
        if(baseText.length() > textCount) {
            arrangeText.append(baseText.charAt(textCount));
            textCount++;
        }else{
            isEnd = true;
        }
    }

    @Override
    public void runningProperty(Graphics2D og, Graphics g, Properties properties, Animation<?> animation) {
        Animation<String> animation1 = (Animation<String>) animation;
        if(!arrangeText.isEmpty() && !isEnd)
            animation1.setViewObject(arrangeText.toString());
    }

    @Override
    public boolean isEnd() {
        return isEnd;
    }
}
