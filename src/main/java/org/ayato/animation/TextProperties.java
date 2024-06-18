package org.ayato.animation;

import org.ayato.animation.text.properties.*;
import org.ayato.animation.text.properties.Button;
import org.ayato.animation.text.properties.Frame;
import org.ayato.component.Transform;
import org.ayato.system.LunchScene;
import org.ayato.component.Position;

import java.awt.*;
import java.util.function.*;

public final class TextProperties extends Properties<String>{
    public TextProperties(int x, int y, int w, int h){
        super(new Transform(x, y, w, h));
    }

    public TextProperties button(int bx, int by, int bw, int bh, AnimationState state, PropertyAction<Button> action){
        Transform p = new Transform(bx, by, bw, bh);
        p.position.setXAddon(()-> transform.position.getNormalX()).setYAddon(()-> transform.position.getNormalY());
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
        properties.add(()->new CenterFrame(transform.getPosition().y(), bw, bh, frameCol, backColor));
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

    public TextProperties checkBox(Consumer<Boolean> buttonAction, AnimationState state, Color checkColor, CheckBox.Duration duration){
        properties.add(()->new CheckBox(buttonAction,state, checkColor, duration));
        return this;
    }
    public TextProperties chooseBox(Consumer<Boolean> buttonAction, AnimationState state, Color checkColor, CheckBox.Duration duration){
        properties.add(()->new ChooseBox(buttonAction, state, checkColor, duration));
        return this;
    }
    public TextProperties fadeIn(long time){
        properties.add(()->new FadeIn(time));
        return this;
    }
    public TextProperties displayInOrder(long time){
        properties.add(()->new DisplayInOrder(time));
        return this;
    }
    public TextProperties fadeOut(long time){
        return fadeOut(time, ()->true);
    }
    public TextProperties fadeOut(long time, BooleanSupplier condition){
        properties.add(()-> new FadeOut(time, condition));
        return this;
    }
    public TextProperties font(String font, int style, float size){
        properties.add(0,()-> (og, g, properties, animation) -> og.setFont(animation.MASTER.getMakeFont(font, style, size)));
        return this;
    }
    public TextProperties frame(Transform transform, AnimationState state){

        properties.add(0, ()->new Frame(transform, state));
        return this;
    }

    public TextProperties size(int size){
        properties.add(()->new IProperty() {
            @Override
            public void runningProperty(Graphics2D og, Graphics g, Properties properties, Animation<?> animation) {
                g.setFont(animation.MASTER.getMakeFont("", Font.PLAIN, size));
            }
        });
        return this;
    }
    public TextProperties ifView(BooleanSupplier how){
        isVisible = how;
        return this;
    }
    public TextProperties moveTo(int x, int y, long maxTime, MoveTo.VelocityFormat format){
        properties.add(()->new MoveTo(x, y, maxTime, format));
        return this;
    }
    public PropertyMatrix<TextProperties> pushMatrix(){
        PropertyMatrix<TextProperties> matrix = new PropertyMatrix<>(this);
        properties.add(()->matrix);
        return matrix;
    }
    public TextProperties parent(Position position){
        transform.position.addAddon(position);
        return this;
    }
    public TextProperties parent(Animation<?> animation){
        transform.position.addAddon(animation.properties.transform.position);
        return this;
    }

    @Override
    public void run(LunchScene MASTER, Graphics g, String o) {
        g.drawString(o, 10, g.getFontMetrics().getHeight());
    }
}
