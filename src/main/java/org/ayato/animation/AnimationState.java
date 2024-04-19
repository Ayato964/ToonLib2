package org.ayato.animation;

import java.awt.*;

public class AnimationState {
    public static final int BACKGROUND = -1;
    public static final int FRAME = -2;

    public static final int DEFAULT = 0;
    public static final int OVERLAP = 1;
    public static final int PRESS = 2;

    private Color[] frame_color = new Color[3];
    private Color[] background_color = new Color[3];
    private int ID = DEFAULT;
    public AnimationState(AnimationState state){
        this(state.frame_color[DEFAULT],state.frame_color[OVERLAP],state.frame_color[PRESS],
                state.background_color[DEFAULT],state.background_color[OVERLAP],state.background_color[PRESS]
                );
    }

    public AnimationState(Color f_def, Color f_over, Color f_press, Color b_def, Color b_over, Color b_press) {
        frame_color[0] = f_def;
        frame_color[1] = f_over;
        frame_color[2] = f_press;
        background_color[0] = b_def;
        background_color[1] = b_over;
        background_color[2] = b_press;
    }
    public void setState(int state){
        ID = state;
    }
    public Color getState(int f){
        return switch (f){
            case BACKGROUND -> background_color[ID];
            case FRAME -> frame_color[ID];
            default -> Color.RED;

        };
    }
}
