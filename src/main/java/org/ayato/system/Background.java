package org.ayato.system;

import org.ayato.animation.image.ImageMaker;
import org.ayato.util.Display;

import java.awt.*;

public class Background implements Display {
    public BackgroundMode mode;
    private final LunchScene MASTER;
   // private ImageMaker bgImage;

    public Background(LunchScene scene){
        MASTER = scene;
        //bgImage = new ImageMaker("title/background", 256, 144);
        mode = BackgroundMode.COLOR;
        mode.setColor(Color.CYAN);
    }

    @Override
    public void display(Graphics g) {
        Rectangle r = MASTER.FRAME.DESCTOP_BOUNDS;
        switch (mode) {
            case COLOR -> {
                g.setColor(mode.getColor());
                g.fillRect(0, 0, r.width, r.height);
            }
            case IMAGE -> g.drawImage(mode.getImage().getEditImage(), 0, 0, r.width, r.height, null);
            default -> throw new IllegalStateException("Unexpected value: " + mode);
        }


    }

    public void setMode(BackgroundMode mode) {
        this.mode = mode;
    }

    public enum BackgroundMode{
        COLOR("color"),
        IMAGE("image");

        private final String a;
        private Color c;
        private ImageMaker image;
        BackgroundMode(String i) {
            a = i;
        }

        public String get() {
            return a;
        }

        public void setColor(Color c) {
            this.c = c;
        }

        public Color getColor() {
            return c;
        }

        public void setImage(ImageMaker image) {
            this.image = image;
        }

        public ImageMaker getImage() {
            return image;
        }
    }
}
