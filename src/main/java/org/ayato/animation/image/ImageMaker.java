package org.ayato.animation.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ImageMaker {
    private static String basePath = "assets/ayato/";
    private Image editImage;
    private BufferedImage original;

    public ImageMaker(String directory, String filename){
        URL path = getClass().getClassLoader().getResource(basePath + directory + "/" + filename + ".png");

        try {
            original = ImageIO.read(Objects.requireNonNull(path));
            editImage = original;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Image getEditImage() {
        return editImage;
    }
}
