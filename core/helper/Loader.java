package core.helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Loader {
    
    public static BufferedImage loadImage(String path) {
        try {
            InputStream imageStream = Loader.class.getClassLoader().getResourceAsStream(path);
            if (imageStream != null) {
                return ImageIO.read(imageStream);
            }
            File file = new File(path);
            if (file.exists()) { 
                return ImageIO.read(file);
            }
        } catch (IOException e) {
            System.err.println("Error loading window icon: " + path);
        }
        return null;
    }

}
