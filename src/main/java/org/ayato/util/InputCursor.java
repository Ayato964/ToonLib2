package org.ayato.util;

import org.ayato.component.Position;
import org.ayato.system.ToonMaster;

import java.awt.*;

public class InputCursor {
    public boolean isInputMode = false;
    private boolean isVisible = true;
    private long count = 0, maxDisplayTime;
    private Color def;
    private final ToonMaster MASTER;
    public InputCursor(long displayTime, Color defColor, ToonMaster master){
        def = defColor;
        MASTER = master;
        maxDisplayTime = displayTime;
    }
    public void display(Graphics g, String str, Position position){
        if(isInputMode && isVisible) {
            int x = 0, y = 0;
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
