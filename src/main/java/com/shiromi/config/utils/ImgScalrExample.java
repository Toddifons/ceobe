package com.shiromi.config.utils;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
@Component
public class ImgScalrExample {
    public void doImgScalr(String path) {
        try {
            // Load the original image
            BufferedImage originalImage = ImageIO.read(new File(path));

            // Resize the image to 200x150 pixels using the default method (ULTRA_QUALITY)
            BufferedImage resizedImage = Scalr.resize(originalImage, Scalr.Method.ULTRA_QUALITY, 200, 150);

            // Save the resized image to a file
            File outputfile = new File(path);
            ImageIO.write(resizedImage, "jpg", outputfile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


