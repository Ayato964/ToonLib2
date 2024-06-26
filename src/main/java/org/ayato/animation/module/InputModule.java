package org.ayato.animation.module;

import org.ayato.animation.*;
import org.ayato.animation.text.properties.Button;
import org.ayato.system.ToonMaster;
import org.ayato.util.InputCursor;
import org.ayato.util.KeyInputs;
import org.ayato.util.MouseInputs;
import org.ayato.util.PropertiesSupplier;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InputModule extends Animation<String> implements KeyListener {
    private final InputCursor inputCursor;
    private final String baseMessage;
    private final StringBuilder inputMessage = new StringBuilder();
    private boolean isClicked = false;
    private final AnimationState STATE= new AnimationState(
            Color.WHITE,
            Color.BLUE,
            Color.RED,
            new Color(0, 0, 0, 190),
            new Color(0, 0, 0, 190),
            new Color(0, 0, 0, 190)
    );
    private int width, height;
    private final Consumer<String> ifPressEnter;
    private final PropertiesSupplier<TextProperties> INPUT_PROP = (x, y) ->
            PropertiesComponent.ofText(x, y)
                    .color(Color.WHITE)
                    .font("", Font.PLAIN, 1.0f)
                    .button(0, 0, width, height, STATE, this::pressed);

    private void pressed(Button property) {
        isClicked = !isClicked;
        if (!isClicked) {
            ifPressEnter.accept(inputMessage.toString());
            if (inputMessage.isEmpty())
                mes = baseMessage;
            inputCursor.isInputMode = false;
            KeyInputs.setLock(false);
        } else{
            MouseInputs.setLock(true);
            KeyInputs.setLock(true);
            inputCursor.isInputMode = true;
            if (inputMessage.isEmpty()) {
                mes = "";
            }
            MASTER.SCENE.addEndTask(()->MASTER.FRAME.addKeyListener(this));
        }
    }

    @Override
    public void init() {
        super.init();
        //MASTER.FRAME.addKeyListener(this);
    }

    public InputModule(ToonMaster master, Supplier<String> a, TextProperties prop, int width, int height, Consumer<String> ifPressEnter) {
        super(master, a, prop);
        this.width = width;
        this.height = height;
        this.properties = INPUT_PROP.of(prop.transform.getPosition().x(), prop.transform.getPosition().y()).copyAddon(prop);
        this.ifPressEnter = ifPressEnter;
        this.baseMessage = a.get();
        inputCursor = new InputCursor(20l, Color.WHITE, master);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if(isClicked) {
            if (keyEvent.getKeyChar() != '\b' && keyEvent.getKeyChar() != '\n'){
                inputMessage.append(keyEvent.getKeyChar());
                mes = inputMessage.toString();
            }else if(keyEvent.getKeyChar() == '\b'){
                if (!inputMessage.isEmpty()) {
                    inputMessage.deleteCharAt(inputMessage.length() - 1);
                    mes = inputMessage.toString();
                }
            }else{
                MASTER.SCENE.addEndTask(()->{
                    MASTER.FRAME.removeKeyListener(this);
                    MouseInputs.setLock(false);});

            }

        }


    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void display(Graphics g) {
        Graphics2D g2 = (Graphics2D) baseGraphics.getGraphics();
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, properties.transform.getW(), properties.transform.getH());
        g2.setComposite(AlphaComposite.Src);



        if (properties != null)
            properties.runProp(g2, g, this);
        if(mes != null)
            properties.run(g2, mes, baseGraphics.getWidth() / 2, baseGraphics.getHeight() / 2);

        inputCursor.display(g2, mes, properties.transform.position);
        g.drawImage(baseGraphics, properties.transform.getPosition().x(), properties.transform.getPosition().y(),
                properties.transform.getW(), properties.transform.getH(), null);
        Color now = g.getColor();
        g.setColor(new Color(now.getRGB()));

        Color now2 = g2.getColor();
        g2.setColor(new Color(now2.getRGB()));
    }
}
