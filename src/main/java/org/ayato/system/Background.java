package org.ayato.system;

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
            case COLOR:g.setColor(mode.getColor()); g.fillRect(0, 0, r.width, r.height);break;
            case IMAGE:g.drawImage(mode.getImage(), 0, 0, r.width, r.height, null);break;
        }


    }

    public void setMode(BackgroundMode mode) {
        this.mode = mode;
    }

    public enum BackgroundMode{
        COLOR("color"),
        IMAGE("image");

        private String a;
        private Color c;
        private Image image;
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

        public void setImage(Image image) {
            this.image = image;
        }

        public Image getImage() {
            return image;
        }
    }
}
