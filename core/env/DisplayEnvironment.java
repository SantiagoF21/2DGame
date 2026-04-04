package core.env;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public final class DisplayEnvironment {

    private DisplayEnvironment() {}

    private static final GraphicsEnvironment GRAPHICS_ENVIRONMENT = GraphicsEnvironment.getLocalGraphicsEnvironment();

    public static final GraphicsDevice[] ALL_DEVICES = GRAPHICS_ENVIRONMENT.getScreenDevices();

    public static final GraphicsDevice PRIMARY_DEVICE = GRAPHICS_ENVIRONMENT.getDefaultScreenDevice();

    public static final Rectangle PRIMARY_DEVICE_BOUNDS = PRIMARY_DEVICE.getDefaultConfiguration().getBounds();

    public static final int SCREEN_WIDTH = PRIMARY_DEVICE_BOUNDS.width;
    public static final int SCREEN_HEIGHT = PRIMARY_DEVICE_BOUNDS.height;

    public static final int SCREEN_X = PRIMARY_DEVICE_BOUNDS.x;
    public static final int SCREEN_Y = PRIMARY_DEVICE_BOUNDS.y;

}
