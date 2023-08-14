package org.ayato.animation;

import org.ayato.animation.text.properties.*;
import org.ayato.animation.text.properties.Frame;

import java.awt.*;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TextProperties extends Properties{
    public TextProperties(int x, int y){
        super(x, y);
    }

    public TextProperties button(int bx, int by, int bw, int bh, Supplier<Color> def, Color bg, PropertyAction insert, PropertyAction action){
        properties.add(new org.ayato.animation.text.properties.Frame(()->bx, ()->by, bw, bh, def, bg));
        properties.add(new org.ayato.animation.text.properties.Button(bx, by, bw, bh, insert, action));
        return this;
    }
    public TextProperties button(int bw, int bh, Supplier<Color> def, Color bg, PropertyAction insert, PropertyAction action){
        properties.add(new org.ayato.animation.text.properties.Frame(()->x, ()->y, bw, bh, def, bg));
        properties.add(new org.ayato.animation.text.properties.Button(x, y, bw, bh, insert, action));
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

    public TextProperties centerFrame(int bw, int bh, Supplier<Color> frameCol, Color backColor){
        properties.add(new CenterFrame(y, bw, bh, frameCol, backColor));
        return this;
    }
    public TextProperties color(Color color){
        properties.add(new IProperty() {
            @Override
            public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
                g.setColor(color);
            }

            @Override
            public void reset(int nx, int ny) {

            }
        });
        return this;
    }
    public TextProperties changeMessage(Supplier<String> str){
        properties.add((new IProperty() {
            @Override
            public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
                Animation<String> a = (Animation<String>) animation;
                a.setViewObject(str.get());
            }

            @Override
            public void reset(int nx, int ny) {

            }
        } ));
        return this;
    }
    public TextProperties font(Font font){
        properties.add(0, new IProperty() {
            @Override
            public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
                g.setFont(font);
            }

            @Override
            public void reset(int nx, int ny) {

            }
        });
        return this;
    }
    public TextProperties frame(int bx, int by, int bw, int bh, Supplier<Color> frameCol, Color backColor){
        properties.add(0, new org.ayato.animation.text.properties.Frame(()->bx, ()->by, bw, bh, frameCol, backColor));
        return this;
    }
    public TextProperties frame(int bw, int bh, Supplier<Color> frameCol, Color backColor){
        properties.add(0, new Frame(()->x, ()->y,bw, bh, frameCol, backColor));
        return this;
    }
    public TextProperties talk(Object key, boolean stopAll, PropertyAction action, Supplier<String> ... strings){
        properties.add( new Talk(strings, key, stopAll, action));
        return this;
    }
    public TextProperties size(int size){
        properties.add(new IProperty() {
            @Override
            public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
                g.setFont(new Font("", Font.PLAIN, size));
            }

            @Override
            public void reset(int nx, int ny) {

            }
        });
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

    @Override
    public TextProperties getInstance() {
        return this;
    }

}
