package org.ayato.animation;

import org.ayato.component.Position;
import org.ayato.component.Transform;

public class PropertiesComponent {
    public static TextProperties ofText(){
        return ofText(0, 0);

    }
    public static TextProperties ofText(int x, int y, int w, int h){
        return new TextProperties(x, y, w, h);
    }
    public static TextProperties ofText(int x, int y) {
        return ofText(x, y, 200, 70);
    }
    public static ImageProperties ofImage(){
        return ofImage(0, 0, 0, 0);
    }

    public static ImageProperties ofImage(int i, int i1, int i2, int i3) {
        return new ImageProperties(new Transform(i, i1, i2, i3));
    }

}
