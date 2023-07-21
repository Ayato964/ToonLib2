package org.ayato.animation;

import org.ayato.animation.image.ImageMaker;
import org.ayato.animation.text.properties.*;
import org.ayato.animation.text.properties.Button;
import org.ayato.animation.text.properties.Frame;
import org.ayato.system.LunchScene;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Properties<T>{
    public int mx, my;
    public int x, y, w, h;
    protected final ArrayList<IProperty<T>> properties;
    protected Animation<T> animation;
    protected BooleanSupplier booleanSupplier;
    protected DisplayAnimation<T> runAnimation;
    protected Properties()
    {
        properties = new ArrayList<>();
    }
    public void addAnimation(Animation<T> text){
        animation = text;
        animation.displayAnimation = runAnimation;
    }

    public  static TextProperties ofText(){
        return ofText(0, 0);
    }
    public  static TextProperties ofText(int x, int y){
        final TextProperties properties1 = new TextProperties();
        properties1.x = x;
        properties1.y = y;
        properties1.mx = x;
        properties1.my = y;
        properties1.runAnimation = properties1.getTextAnimation();

        return properties1;
    }
    public static Properties.ImageProperties ofImage(){
        return ofImage(0, 0, 0, 0);
    }

    public static Properties.ImageProperties ofImage(int x, int y, int w, int h){
        final ImageProperties properties1 =  new ImageProperties();
        properties1.x = x;
        properties1.y = y;
        properties1.w = w;
        properties1.h = h;
        properties1.mx = x;
        properties1.my = y;
        properties1.runAnimation = properties1.getImageAnimation();

        return properties1;
    }
    protected DisplayAnimation<String> getTextAnimation(){
        return (MASTER, g, o) -> g.drawString(o, x * MASTER.FRAME.DW, y * MASTER.FRAME.DH);
    }
    protected DisplayAnimation<ImageMaker> getImageAnimation(){
        return (MASTER, g, o)-> g.drawImage(o.getEditImage(),
                x * MASTER.FRAME.DW, y * MASTER.FRAME.DH
                , w * MASTER.FRAME.DW, h * MASTER.FRAME.DH, null);
    }

    public void runProp(Graphics g){
        if(booleanSupplier != null) {
            animation.bool = booleanSupplier;
            booleanSupplier = null;
            return;
        }
        for(IProperty<T> p : properties)
            p.runningProperty(g, this, animation);
    }
    public Properties<T> setSize(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }

    public Properties<T> setX(int i) {
        x = i;
        return this;
    }

    public Properties<T> setY(int y) {
        this.y = y;
        return this;
    }

    public Properties<T> setW(int w) {
        this.w = w;
        return this;
    }

    public Properties<T> setH(int h) {
        this.h = h;
        return this;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
    public Properties<T> getInstance(){
        return this;
    }
    public void reset(){
        x = mx;
        y = my;
        for(IProperty<T> p : properties){
            p.reset(mx, my);
        }
    }

    public <M extends Properties<T>> M copy() {
        Properties<T> t = this instanceof TextProperties
                ? (Properties<T>) new TextProperties()
                : (Properties<T>) new ImageProperties();
        t.x = x;
        t.y = y;
        t.h = h;
        t.w = w;
        t.runAnimation = this instanceof TextProperties ? (DisplayAnimation<T>) t.getTextAnimation() : (DisplayAnimation<T>) t.getImageAnimation();

        t.properties.addAll((Collection<? extends IProperty<T>>) properties.clone());
        return (M) t;
    }


    public static class TextProperties extends Properties<String>{

        public TextProperties button(int bx, int by, int bw, int bh, Supplier<Color> def, Color bg, PropertyAction insert, PropertyAction action){
            properties.add(new Frame(()->bx, ()->by, bw, bh, def, bg));
            properties.add(new Button<>(bx, by, bw, bh, insert, action));
            return this;
        }
        public TextProperties button(int bw, int bh, Supplier<Color> def, Color bg, PropertyAction insert, PropertyAction action){
            properties.add(new Frame(()->x, ()->y, bw, bh, def, bg));
            properties.add(new Button<>(x, y, bw, bh, insert, action));
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
            properties.add(new IProperty<String>() {
                @Override
                public void runningProperty(Graphics g, Properties<String> properties, Animation<String> animation) {
                    g.setColor(color);
                }

                @Override
                public void reset(int nx, int ny) {

                }
            });
            return this;
        }
        public TextProperties changeMessage(Supplier<String> str){
            properties.add((new IProperty<String>() {
                @Override
                public void runningProperty(Graphics g, Properties<String> properties, Animation<String> animation) {
                    animation.setViewObject(str.get());
                }

                @Override
                public void reset(int nx, int ny) {

                }
            } ));
            return this;
        }
        public TextProperties font(Font font){
            properties.add(0, new IProperty<String>() {
                @Override
                public void runningProperty(Graphics g, Properties<String> properties, Animation<String> animation) {
                    g.setFont(font);
                }

                @Override
                public void reset(int nx, int ny) {

                }
            });
            return this;
        }
        public TextProperties frame(int bx, int by, int bw, int bh, Supplier<Color> frameCol, Color backColor){
            properties.add(0, new Frame(()->bx, ()->by, bw, bh, frameCol, backColor));
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
            properties.add(new IProperty<String>() {
                @Override
                public void runningProperty(Graphics g, Properties<String> properties, Animation<String> animation) {
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

    public static class ImageProperties extends Properties<ImageMaker> {

        public ImageProperties button(int bx, int by, int bw, int bh, PropertyAction insert, PropertyAction action){
            properties.add(new Button<>(bx, by, bw, bh, insert, action));
            return this;
        }
        public ImageProperties button(PropertyAction insert, PropertyAction action){
            properties.add(new Button<>(x, y, w, h, insert, action));
            return this;
        }
        public ImageProperties ifView(BooleanSupplier how){
            booleanSupplier = how;
            return this;
        }

        @Override
        public ImageProperties getInstance() {
            return this;
        }
    }

}
