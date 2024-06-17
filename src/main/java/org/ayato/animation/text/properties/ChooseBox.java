package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.AnimationState;
import org.ayato.animation.Properties;
import org.ayato.util.Display;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ChooseBox extends CheckBox{
    private String groupID;
    private Animation<?> animation;
    private boolean isFirst = true;
    public ChooseBox(Consumer<Boolean> buttonAction, AnimationState state, Color checkColor, Duration duration) {
        super(buttonAction, state, checkColor, duration);
    }

    @Override
    public void runningProperty(Graphics g, Properties properties, Animation<?> animation) {
        if(isFirst) {
            this.animation = animation;
            isFirst = false;
        }
        positionUpdate(properties.position, g, animation);
        groupID = animation.getGroup();
        g.setColor(state.getState(AnimationState.FRAME));
        g.drawOval(position.getX(), position.getY(), position.getW() , position.getH());
        if(isClicked){
            g.setColor(checkColor);
            g.fillOval(position.getX() + 1 * animation.MASTER.DW,  position.getY() + 1 * animation.MASTER.DH,
                    position.getW() -2* animation.MASTER.DW, position.getH() -2 * animation.MASTER.DH);
        }
    }

    @Override
    public void press() {
        isClicked = true;
        buttonAction.accept(isClicked);
        unCheckOtherChooseBox();

    }
    public void unClick(){
        isClicked = false;
        buttonAction.accept(isClicked);
    }

    private void unCheckOtherChooseBox() {
        ArrayList<Display> displays = animation.MASTER.getComponentsGroupID(groupID);
        for(Display d : displays){
            if(d instanceof Animation<?> animation1){
                if(!animation1.equals(animation)) {
                    ChooseBox chooseBoxes = animation1.getPropertyClass(ChooseBox.class);
                    if (chooseBoxes != null)
                        chooseBoxes.unClick();
                }
            }
        }
    }
}
