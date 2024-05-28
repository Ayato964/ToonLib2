package org.ayato.animation;

import org.ayato.animation.text.properties.*;
import org.ayato.animation.text.properties.Button;
import org.ayato.animation.text.properties.Frame;
import org.ayato.system.LunchScene;
import org.ayato.util.Position;

import java.awt.*;
import java.util.function.*;

public final class TextProperties extends Properties<String>{
    public TextProperties(int x, int y){
        super(x, y);
    }

    public TextProperties button(int bx, int by, int bw, int bh, AnimationState state, PropertyAction<Button> action){
        Position p = new Position(bx, by, bw, bh).setXAddon(()->position.getX()).setYAddon(()->position.getY() );
        Supplier<Frame> f = ()->new org.ayato.animation.text.properties.Frame(p, state);
        properties.add(()->new org.ayato.animation.text.properties.Button(p, action, f.get()));
        return this;
    }
    public TextProperties center(){
        properties.add(Center::new);
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
        properties.add(()->new ChangeColor(color));
        return this;
    }
    public TextProperties copyAddon(TextProperties prop){
        if(prop != null) {
            isVisible = prop.isVisible;
            properties.addAll(prop.properties);
        }
        return this;
    }
    public TextProperties custom(IProperty prop){
        properties.add(()->prop);
        return this;
    }
    public TextProperties customAll(IProperty[] props){
        for(IProperty p : props)
            properties.add(()->p);
        return this;
    }
    public TextProperties changeText(ChangeFunction<String> condition){
        properties.add(()->new Change<String>(condition));
        return this;
    }
    public TextProperties checkBox(Consumer<Boolean> buttonAction, AnimationState state, Color checkColor, CheckBox.Duration duration){
        properties.add(()->new CheckBox(buttonAction,state, checkColor, duration));
        return this;
    }
    public TextProperties chooseBox(Consumer<Boolean> buttonAction, AnimationState state, Color checkColor, CheckBox.Duration duration){
        properties.add(()->new ChooseBox(buttonAction, state, checkColor, duration));
        return this;
    }
    public TextProperties font(String font, int style, float size){
        properties.add(0,()-> new IProperty() {
            @Override
            public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
                g.setFont(animation.MASTER.getMakeFont(font, style, size));
            }
        });
        return this;
    }
    public TextProperties frame(Position position, AnimationState state){

        properties.add(0, ()->new Frame(position, state));
        return this;
    }

    public TextProperties size(int size){
        properties.add(()->new IProperty() {
            @Override
            public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
                g.setFont(animation.MASTER.getMakeFont("", Font.PLAIN, size));
            }
        });
        return this;
    }
    public TextProperties ifView(BooleanSupplier how){
        isVisible = how;
        return this;
    }

    @Override
    public void run(LunchScene MASTER, Graphics g, String o) {
        g.drawString(o, position.getX() * MASTER.DW, position.getY() * MASTER.DH + g.getFontMetrics().getHeight());
    }
}
