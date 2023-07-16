package org.ayato.animation.properties;

import org.ayato.AbstractProperties;
import org.ayato.animation.AbstractAnimation;
import org.ayato.system.AnimationText;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TextProperties extends AbstractProperties<AnimationText>{



    public TextProperties button(int bx, int by, int bw, int bh, Supplier<Color> def, Color bg, PropertyAction insert, PropertyAction action){
        properties.add(new Frame(bx, by, bw, bh, def, bg));
        properties.add(new Button(bx, by, bw, bh, insert, action));
        return this;
    }
    public TextProperties center(){
        properties.add(new Center());
        return this;
    }
    public TextProperties centerFrame(int by, int bw, int bh, Supplier<Color> frameCol, Color backColor){
        properties.add(new CenterFrame(by, bw, bh, frameCol, backColor));
        return this;
    }
    public TextProperties color(Color color){
        properties.add((g, properties1, text) -> g.setColor(color));
        return this;
    }
    public TextProperties changeMessage(Supplier<String> str){
        properties.add((g, properties1, text) -> animation.setViewObject(str.get()));
        return this;
    }
    public TextProperties font(Font font){
        properties.add(0, ((g, properties1, text) -> g.setFont(font)));
        return this;
    }
    public TextProperties frame(int bx, int by, int bw, int bh, Supplier<Color> frameCol, Color backColor){
        properties.add(0, new Frame(bx, by, bw, bh, frameCol, backColor));
        return this;
    }
    public TextProperties talk(Object key, boolean stopAll, PropertyAction action, String ... strings){
        properties.add(new Talk(strings, key, stopAll, action));
        return this;
    }
    public TextProperties size(int size){
        properties.add(((g, properties1, text) -> g.setFont(new Font("", Font.PLAIN, size))));
        return this;
    }
    public TextProperties ifView(BooleanSupplier how){
        booleanSupplier = how;
        return this;
    }
    public TextProperties input(int bx, int by, int bw, int bh, Consumer<String> stringConsumer){
        properties.add(new Input(bx, by, bw, bh, stringConsumer));
        return this;
    }

    public TextProperties copy() {
        TextProperties properties1 = new TextProperties();
        properties1.properties.addAll((ArrayList<IProperty>) properties.clone());
        return properties1;
    }
}
