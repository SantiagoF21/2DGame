package core.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;

import core.env.DisplayEnvironment;

public class WindowBuilder {

    /* --- Defaults --- */
    
    private int width = 640;
    private int height = 360;

    private int xCoord = (DisplayEnvironment.SCREEN_WIDTH - width) / 2;
    private int yCoord = (DisplayEnvironment.SCREEN_HEIGHT - height) / 2;

    private Color backgroundColor = Color.BLACK;

    private boolean isFocusable = true;

    private String title = "New Window";
    private String appImageFilePath = "core/window/DefaultAppImage.png";

    private LayoutManager layout = null;
    private boolean isManuallyResizable = true;

    private boolean isDoubleBuffered = true;

    private boolean isOpaque = true;

    private boolean isDecorated = true;

    private volatile Shutdown shutdown = () -> {
        final int EXIT_PROGRAM = 0;
        System.exit(EXIT_PROGRAM);
    };

    /* --- Sizing --- */

    public WindowBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public WindowBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public WindowBuilder setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public WindowBuilder setSize(Dimension dimension) {
        this.width = dimension.width;
        this.height = dimension.height;
        return this;
    }

    /* --- Coordinates --- */

    public WindowBuilder setXCoord(int xCoord) {
        this.xCoord = xCoord;
        return this;
    }

    public WindowBuilder setYCoord(int yCoord) {
        this.yCoord = yCoord;
        return this;
    }

    public WindowBuilder setCoords(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        return this;
    }

    public WindowBuilder setCoords(Point coords) {
        this.xCoord = coords.x;
        this.yCoord = coords.y;
        return this;
    }

    public WindowBuilder center() {
        this.xCoord = (DisplayEnvironment.SCREEN_WIDTH - width) / 2;
        this.yCoord = (DisplayEnvironment.SCREEN_HEIGHT - height) / 2;
        return this;
    }

    /* --- Appearance --- */

    public WindowBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public WindowBuilder setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public WindowBuilder setBackgroundColor(int red, int green, int blue) {
        this.backgroundColor = new Color(red, green, blue);
        return this;
    }

    public WindowBuilder setAppImageFilePath(String appImageFilePath) {
        this.appImageFilePath = appImageFilePath;
        return this;
    }

    public WindowBuilder setDecorated(boolean isDecorated) {
        this.isDecorated = isDecorated;
        return this;
    }

    /* --- Behavior --- */

    public WindowBuilder setFocusable(boolean isFocusable) {
        this.isFocusable = isFocusable;
        return this;
    }

    public WindowBuilder setLayout(LayoutManager layout) {
        this.layout = layout;
        return this;
    }

    public WindowBuilder setManuallyResizable(boolean isManuallyResizable) {
        this.isManuallyResizable = isManuallyResizable;
        return this;
    }

    public WindowBuilder setDoubleBuffered(boolean isDoubleBuffered) {
        this.isDoubleBuffered = isDoubleBuffered;
        return this;
    }

    public WindowBuilder setOpaque(boolean isOpaque) {
        this.isOpaque = isOpaque;
        return this;
    }

    public WindowBuilder setShutdown(Shutdown shutdown) {
        this.shutdown = shutdown;
        return this;
    }

    /* --- Validation & Building --- */

    private void validate() {
        if (width <= 0) {
            throw new IllegalStateException("Width must be positive number; Width: " + width);
        } if (height <= 0) {
            throw new IllegalStateException("Height must be positive number; Height: " + height);
        } if (title == null || title.trim().isEmpty()) {
            throw new IllegalStateException("Title cannot be null or empty; Title: " + title);
        } if (backgroundColor == null) {
            throw new IllegalStateException("Background Color cannot be null.");
        }
    }

    public Window build() {
        validate();
        return new Window(width, height, xCoord, yCoord, backgroundColor, isFocusable, title, appImageFilePath, layout, isManuallyResizable, isDoubleBuffered, isOpaque, isDecorated, shutdown);
    }

}
