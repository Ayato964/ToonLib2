package org.ayato.animation.module;

import org.ayato.animation.*;
import org.ayato.animation.text.properties.IProperty;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.LunchScene;
import org.ayato.util.PropertiesSupplier;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

public class InputModule extends Animation<String> implements KeyListener {
    private String baseMessage;
    private StringBuilder inputMessage = new StringBuilder();
    private boolean isClicked = false;
    private AnimationState STATE= new AnimationState(
            Color.WHITE,
            Color.BLUE,
            Color.RED,
            new Color(0, 0, 0, 190),
            new Color(0, 0, 0, 190),
            new Color(0, 0, 0, 190)
    );
    private final Consumer<String> ifPressEnter;
    private PropertiesSupplier<TextProperties> INPUT_PROP = (x, y) ->
            PropertiesComponent.ofText(x, y)
                    .color(Color.WHITE)
                    .font("", Font.PLAIN, 1.0f)
                    .button(0, 0, 20, 10, STATE, this::pressed);

    private void pressed(IProperty property) {
        isClicked = !isClicked;
        if(!isClicked){
            ifPressEnter.accept(inputMessage.toString());
            if(inputMessage.isEmpty())
                mes = baseMessage;
        }else if(inputMessage.isEmpty()){
            mes = "";
        }
    }
    @Override
    public void init() {
        super.init();
        MASTER.FRAME.addKeyListener(this);
    }

    public InputModule(LunchScene master, String a, TextProperties prop, Consumer<String> ifPressEnter) {
        super(master, a, prop);
        this.properties = INPUT_PROP.of(prop.baseX, prop.baseY).copyAddon(prop);
        this.ifPressEnter = ifPressEnter;
        this.baseMessage = a;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if(isClicked) {
            if (keyEvent.getKeyCode() != KeyEvent.VK_BACK_SPACE &&
                keyEvent.getKeyCode() != KeyEvent.VK_ENTER && keyEvent.getKeyChar() != '\n'
            ) {
                inputMessage.append(keyEvent.getKeyChar());
                mes = inputMessage.toString();
            }
        }


    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(isClicked && keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            if (inputMessage.length() > 0) {
                inputMessage.deleteCharAt(inputMessage.length() - 1);
                mes = inputMessage.toString();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}