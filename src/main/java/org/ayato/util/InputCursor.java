package org.ayato.util;

import org.ayato.system.LunchScene;

import java.awt.*;

public class InputCursor {
    public boolean isInputMode = false;
    private boolean isVisible = true;
    private long count = 0, maxDisplayTime;
    private Color def;
    private final LunchScene MASTER;
    public InputCursor(long displayTime, Color defColor, LunchScene master){
        def = defColor;
        MASTER = master;
        maxDisplayTime = displayTime;
    }
    public void display(Graphics g, String str, Position position){
        if(isInputMode && isVisible) {
            int x = position.getX(), y = position.getY();
            x += g.getFontMetrics().stringWidth(str);
            g.setColor(def);
            g.fillRect(x, y, 2 * MASTER.DW, g.getFontMetrics().getHeight());
        }
        count ++;
        if(count > maxDisplayTime){
            isVisible = !isVisible;
            count = 0;
        }
    }
}
