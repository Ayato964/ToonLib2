package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;
import org.ayato.animation.text.properties.Order;

import java.awt.*;

public class AnimationSequentialOrder implements IProperty {
    private IteratorList<IProperty> properties;
    private Properties prop;

    public AnimationSequentialOrder(Properties t){
        prop = t;
    }
    private boolean isEnd = false;
    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
        if(!isEnd && properties != null)
            this.properties.get().runningProperty(g, properties, animation);

    }


    @Override
    public void reset(int nx, int ny) {
    }
    @Override
    public void endTask() {
        if(properties.hasNext())
            properties = properties.next();
        else
            isEnd = true;

    }
    public Properties push(){
        return prop;
    }
    private void add(IProperty p){
        if(properties == null)
            properties = new IteratorList<>(p);
        else
            properties.add(p);
    }


    public AnimationSequentialOrder textDisplayInOrder(long time){
        add(new Order(this, time));
        return this;
    }
}
