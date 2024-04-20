package org.ayato.animation;

import org.ayato.animation.text.properties.*;
import org.ayato.animation.text.properties.Frame;
import org.ayato.system.LunchScene;
import org.ayato.util.Position;

import java.awt.*;
import java.util.function.*;

public class TextProperties extends Properties<String>{
    public TextProperties(int x, int y){
        super(x, y);
    }

    public TextProperties button(int bx, int by, int bw, int bh, AnimationState state, PropertyAction action){
        Position p = new Position(()->position.getX() + bx, ()->position.getY() + by, bw, bh);
        Supplier<Frame> f = ()->new org.ayato.animation.text.properties.Frame(p, state);
        properties.add(()->new org.ayato.animation.text.properties.Button(p, action, f.get()));
        return this;
    }
    public TextProperties center(){
        properties.add(()->new Center());
        return this;
    }
    public TextProperties centerFrame(int by, int bw, int bh, Supplier<Color> frameCol, Color backColor){
        properties.add(()->new CenterFrame(by, bw, bh, frameCol, backColor));
        return this;
    }

    public TextProperties centerFrame(int bw, int bh, Supplier<Color> frameCol, Color backColor){
        properties.add(()->new CenterFrame(position.getY(), bw, bh, frameCol, backColor));
        return this;
    }
    public TextProperties color(Color color){
        properties.add(()->new IProperty() {
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
        properties.add(()->(new IProperty() {
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
    public TextProperties font(String font, int style, float size){
        properties.add(0,()-> new IProperty() {
            @Override
            public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
                g.setFont(animation.MASTER.getMakeFont(font, style, size));
            }

            @Override
            public void reset(int nx, int ny) {

            }
        });
        return this;
    }
    public TextProperties frame(Position position, AnimationState state){
        properties.add(0, ()->new Frame(position, state));
        return this;
    }
    public TextProperties talk(Object key, boolean stopAll, PropertyAction action, Supplier<String> ... strings){
        properties.add( ()->new Talk(strings, key, stopAll, action));
        return this;
    }
    public TextProperties size(int size){
        properties.add(()->new IProperty() {
            @Override
            public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
                g.setFont(animation.MASTER.getMakeFont("", Font.PLAIN, size));
            }

            @Override
            public void reset(int nx, int ny) {

            }
        });
        return this;
    }
    public TextProperties ifView(BooleanSupplier how){
        isVisible = how;
        return this;
    }
    public TextProperties input(int bx, int by, int bw, int bh, Consumer<String> stringConsumer){
        properties.add(()->new Input(bx, by, bw, bh, stringConsumer));
        return this;
    }

    @Override
    public void run(LunchScene MASTER, Graphics g, String o) {
        g.drawString(o, position.getX() * MASTER.DW, position.getY() * MASTER.DH + g.getFontMetrics().getHeight());
    }
}
