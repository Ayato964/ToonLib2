package org.ayato.animation;

import org.ayato.util.Position;

public class PropertiesComponent {
    public static TextProperties ofText(){
        return ofText(0, 0);
    }

    public static TextProperties ofText(int x, int y) {
        return new TextProperties(x, y);
    }
    public static ImageProperties ofImage(){
        return ofImage(0, 0, 0, 0);
    }

    public static ImageProperties ofImage(int i, int i1, int i2, int i3) {
        return new ImageProperties(new Position(i, i1, i2, i3));
    }

}
