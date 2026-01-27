package core.env;

import java.awt.Dimension;
import java.awt.Toolkit;

public class DisplayEnvironment {

    private DisplayEnvironment() {}
    
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    
    public static final int SCREEN_WIDTH = (int)SCREEN_SIZE.getWidth();
    public static final int SCREEN_HEIGHT = (int)SCREEN_SIZE.getHeight();

}
