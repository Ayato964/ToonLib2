package org.ayato.animation.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ImageMaker {
    private static String basePath = "assets/ayato/textures/";
    private Image editImage;
    private BufferedImage original;

    private int tx = 0, ty = 0, tw, th;


    public ImageMaker(String directory, String filename){
        URL path = getClass().getClassLoader().getResource(basePath + directory + "/" + filename + ".png");

        try {
            original = ImageIO.read(Objects.requireNonNull(path));
            editImage = original;
            tw = original.getWidth();
            th = original.getHeight();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ImageMaker(String directory, String filename, int w, int h){
        this(directory, filename);
        tw = w;
        th = h;
        editImage = original.getSubimage(0, 0, w, h);
    }

    public Image getEditImage() {
        return editImage;
    }

    public ImageMaker next(){
        ty += th;
        try {
            editImage = original.getSubimage(0, ty, tw, th);
        }catch (RasterFormatException e){
            ty = 0;
            next();
        }
        return this;
    }
    public int length(){
        int c = 0;
        int count = 0;
        do {
            c += th;
            count ++;
        }while (c < original.getHeight());
        return count;
    }

    public void reset() {
        tx = 0;
        ty = 0;
        editImage = original.getSubimage(tx, ty, tw,th);
    }
}
