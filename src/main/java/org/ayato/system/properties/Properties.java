package org.ayato.system.properties;

import org.ayato.system.AnimationText;
import org.ayato.util.StringSupplier;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Properties {
    private final ArrayList<IProperty> properties;
    private AnimationText animationText;
    private BooleanSupplier booleanSupplier;
    public Properties(){
        properties = new ArrayList<>();
    }
    public void addAnimation(AnimationText text){
        animationText = text;
    }

    public void runProp(Graphics g){
        if(booleanSupplier != null) {
            animationText.bool = booleanSupplier;
            booleanSupplier = null;
            return;
        }
        for(IProperty p : properties)
            p.runningProperty(g, this, animationText);
    }
    public Properties button(int bx, int by, int bw, int bh, Color def, Color bg, PropertyAction insert, PropertyAction action){
        properties.add(new Frame(bx, by, bw, bh, def, bg));
        properties.add(new Button(bx, by, bw, bh, insert, action));
        return this;
    }
    public Properties color(Color color){
        properties.add((g, properties1, text) -> g.setColor(color));
        return this;
    }
    public Properties changeMessage(Supplier<String> str){
        properties.add((g, properties1, text) -> animationText.setMSG(str.get()));
        return this;
    }
    public Properties font(Font font){
        properties.add(((g, properties1, text) -> g.setFont(font)));
        return this;
    }
    public Properties frame(int bx, int by, int bw, int bh, Color frameCol, Color backColor){
        properties.add(new Frame(bx, by, bw, bh, frameCol, backColor));
        return this;
    }
    public Properties talk(Object key, boolean stopAll, PropertyAction action, String ... strings){
        properties.add(new Talk(strings, key, stopAll, action));
        return this;
    }
    public Properties size(int size){
        properties.add(((g, properties1, text) -> g.setFont(new Font("", Font.PLAIN, size))));
        return this;
    }
    public Properties ifView(BooleanSupplier how){
        booleanSupplier = how;
        return this;
    }
    public Properties input(int bx, int by, int bw, int bh, Consumer<String> stringConsumer){
        properties.add(new Input(bx, by, bw, bh, stringConsumer));
        return this;
    }

}
