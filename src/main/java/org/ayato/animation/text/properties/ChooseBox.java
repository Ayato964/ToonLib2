package org.ayato.animation.text.properties;

import org.ayato.animation.Animation;
import org.ayato.animation.AnimationState;
import org.ayato.animation.Properties;
import org.ayato.system.ToonMaster;
import org.ayato.util.ArrayUtils;
import org.ayato.util.Display;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ChooseBox extends CheckBox{
    private String groupID;
    private Animation<?> animation;
    private boolean isFirst = true;
    public ChooseBox(Consumer<Boolean> buttonAction, AnimationState state, Color checkColor, Duration duration) {
        super(buttonAction, state, checkColor, duration);
    }

    @Override
    public void runningProperty(Graphics2D og, Graphics g, Properties properties, Animation<?> animation) {
        if(isFirst) {
            this.animation = animation;
            isFirst = false;
        }

        positionUpdate(properties.transform.position, og, animation);
        groupID = animation.getGroup();
        g.setColor(state.getState(AnimationState.FRAME));
        g.drawOval(transform.getPosition().x(), transform.getPosition().y(), transform.getW() , transform.getH());
        if(isClicked){
            g.setColor(checkColor);
            g.fillOval(transform.getPosition().x() + animation.MASTER.DW,  transform.getPosition().y() +  animation.MASTER.DH,
                    transform.getW() -2* animation.MASTER.DW, transform.getH() -2 * animation.MASTER.DH);
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
        ArrayList<Display> displays = ArrayUtils.searchObjectForGroup(ToonMaster.getINSTANCE().SCENE.getGUI(), groupID);
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
