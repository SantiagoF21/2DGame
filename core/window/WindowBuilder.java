package core.window;

import java.awt.Color;
import java.awt.Dimension;

import core.env.DisplayEnvironment;

public class WindowBuilder {

    private boolean autoCenter = true;

    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 360;

    private static final int DEFAULT_X_COORD = (DisplayEnvironment.SCREEN_WIDTH - DEFAULT_WIDTH) / 2;
    private static final int DEFAULT_Y_COORD = (DisplayEnvironment.SCREEN_HEIGHT - DEFAULT_HEIGHT) / 2;

    private static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
    private static final String DEFAULT_TITLE = "New Window";
    private static final String DEFAULT_APP_IMAGE_FILE_PATH = "core/window/DefaultAppImage.png";

    private static final boolean DEFAULT_IS_RESIZABLE = true;
    private static final boolean DEFAULT_IS_DOUBLE_BUFFERED = false;
    private static final boolean DEFAULT_IS_FOCUSABLE = true;
    private static final boolean DEFAULT_IS_OPAQUE = true;
    private static final boolean DEFAULT_IS_VISIBLE = true;

    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;

    private int xCoord = DEFAULT_X_COORD;
    private int yCoord = DEFAULT_Y_COORD;

    private Color backgroundColor = DEFAULT_BACKGROUND_COLOR;

    private String title = DEFAULT_TITLE;

    private String appImageFilePath = DEFAULT_APP_IMAGE_FILE_PATH;

    private boolean isResizable = DEFAULT_IS_RESIZABLE;
    private boolean isDoubleBuffered = DEFAULT_IS_DOUBLE_BUFFERED;
    private boolean isFocusable = DEFAULT_IS_FOCUSABLE;
    private boolean isOpaque = DEFAULT_IS_OPAQUE;
    private boolean isVisible = DEFAULT_IS_VISIBLE;

    public WindowBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public WindowBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public WindowBuilder setDimension(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public WindowBuilder setDimension(Dimension dimension) {
        this.width = dimension.width;
        this.height = dimension.height;
        return this;
    }

    public WindowBuilder setXCoord(int xCoord) {
        this.xCoord = xCoord;
        this.autoCenter = false;
        return this;
    }

    public WindowBuilder setYCoord(int yCoord) {
        this.yCoord = yCoord;
        this.autoCenter = false;
        return this;
    }

    public WindowBuilder centerOnScreen() {
        this.autoCenter = true;
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

    public WindowBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public WindowBuilder setAppImageFilePath(String appImageFilePath) {
        this.appImageFilePath = appImageFilePath;
        return this;
    }
    
    public WindowBuilder setResizable(boolean isResizable) {
        this.isResizable = isResizable;
        return this;
    }

    public WindowBuilder setDoubleBuffered(boolean isDoubleBuffered) {
        this.isDoubleBuffered = isDoubleBuffered;
        return this;
    }

    public WindowBuilder setFocusable(boolean isFocusable) {
        this.isFocusable = isFocusable;
        return this;
    }

    public WindowBuilder setOpaque(boolean isOpaque) {
        this.isOpaque = isOpaque;
        return this;
    }

    public WindowBuilder setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        return this;
    }

    private void validateWindowFields() {
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
        if (autoCenter) {
            xCoord = (DisplayEnvironment.SCREEN_WIDTH - width) / 2;
            yCoord = (DisplayEnvironment.SCREEN_HEIGHT - height) / 2;
        }
        validateWindowFields();
        return new Window(width, height, xCoord, yCoord, backgroundColor, title, appImageFilePath, isResizable, isDoubleBuffered, isFocusable, isOpaque, isVisible);
    }

}
