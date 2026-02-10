package core.env;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

public final class DisplayEnvironment {

    private DisplayEnvironment() {}

    private static final GraphicsEnvironment DISPLAY_ENVIRONMENT = GraphicsEnvironment.getLocalGraphicsEnvironment();

    public static final GraphicsDevice[] CURRENT_DISPLAY_DEVICES = DISPLAY_ENVIRONMENT.getScreenDevices();
    
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    
    public static final int SCREEN_WIDTH = (int)SCREEN_SIZE.getWidth();
    public static final int SCREEN_HEIGHT = (int)SCREEN_SIZE.getHeight();

}
