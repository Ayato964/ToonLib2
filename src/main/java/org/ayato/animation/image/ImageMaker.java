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
    private BufferedImage editImage;
    private final BufferedImage original;

    private int tx = 0, ty = 0, cutW, cutH;


    public ImageMaker(String directory, String filename){
        URL path = getClass().getClassLoader().getResource(basePath + directory + "/" + filename + ".png");

        try {
            original = ImageIO.read(Objects.requireNonNull(path));
            editImage = original;
            cutW = original.getWidth();
            cutH = original.getHeight();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ImageMaker(String directory, String filename, int w, int h){
        this(directory, filename);
        cutW = w;
        cutH = h;
        editImage = original.getSubimage(tx, ty, w, h);
    }

    public Image get() {
        return editImage;
    }

    public ImageMaker next(){
        ty += cutH;
        try {
            editImage = original.getSubimage(tx, ty, cutW, cutH);
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
            c += cutH;
            count ++;
        }while (c < original.getHeight());
        return count;
    }

    public void reset() {
        tx = 0;
        ty = 0;
        editImage = original.getSubimage(tx, ty, cutW, cutH);
    }
}
