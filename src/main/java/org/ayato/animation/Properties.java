package org.ayato.animation;

import org.ayato.animation.image.ImageMaker;
import org.ayato.animation.text.properties.*;
import org.ayato.animation.text.properties.Button;
import org.ayato.animation.text.properties.Frame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Properties<T>{
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


    public  static Properties.TextProperties ofText(int x, int y){
        final TextProperties properties1 = new TextProperties();
        properties1.x = x;
        properties1.y = y;
        properties1.runAnimation = (MASTER, g, o) -> g.drawString(o, properties1.x * MASTER.FRAME.DW, properties1.y * MASTER.FRAME.DH);
        return properties1;
    }
    public static Properties.ImageProperties ofImage(int x, int y, int w, int h){
        final ImageProperties properties1 =  new ImageProperties();
        properties1.x = x;
        properties1.y = y;
        properties1.w = w;
        properties1.h = h;
        properties1.runAnimation = (MASTER, g, o) -> g.drawImage(o.getEditImage(),
                properties1.x * MASTER.FRAME.DW, properties1.y * MASTER.FRAME.DH
                , properties1.w * MASTER.FRAME.DW, properties1.h * MASTER.FRAME.DH, null);

        return properties1;
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

    public void setX(int i) {
        x = i;
    }


    public static class TextProperties extends Properties<String>{

        public TextProperties button(int bx, int by, int bw, int bh, Supplier<Color> def, Color bg, PropertyAction insert, PropertyAction action){
            properties.add(new Frame(bx, by, bw, bh, def, bg));
            properties.add(new Button<>(bx, by, bw, bh, insert, action));
            return this;
        }
        public TextProperties button(int bw, int bh, Supplier<Color> def, Color bg, PropertyAction insert, PropertyAction action){
            properties.add(new Frame(x, y, bw, bh, def, bg));
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
        public TextProperties frame(int bw, int bh, Supplier<Color> frameCol, Color backColor){
            properties.add(0, new Frame(x, y,bw, bh, frameCol, backColor));
            return this;
        }
        public TextProperties talk(Object key, boolean stopAll, PropertyAction action, String ... strings){
            properties.add( new Talk(strings, key, stopAll, action));
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

        public Properties copy() {
            Properties<String> properties1 = new Properties<>();
            properties1.properties.addAll((Collection<? extends IProperty<String>>) properties.clone());
            return properties1;
        }
    }

    public static class ImageProperties extends Properties<ImageMaker> {

        public ImageProperties button(int bx, int by, int bw, int bh, PropertyAction insert, PropertyAction action){
            properties.add(new Button(bx, by, bw, bh, insert, action));
            return this;
        }
        public ImageProperties ifView(BooleanSupplier how){
            booleanSupplier = how;
            return this;
        }
    }

}
