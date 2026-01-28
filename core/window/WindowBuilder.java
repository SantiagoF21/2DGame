package core.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import core.env.DisplayEnvironment;

public final class WindowBuilder {
    
    private int width = 640;
    private int height = 360;

    private int xCoord = (DisplayEnvironment.SCREEN_WIDTH - width) / 2;
    private int yCoord = (DisplayEnvironment.SCREEN_HEIGHT - height) / 2;

    private boolean autoCenter = true;

    private String title = "New Window";
    private Color backgroundColor = Color.BLACK;

    private boolean isVisible = true;

    private String appImageFilePath = "core/window/DefaultAppImage.png";
    private LayoutManager layout = null;

    private boolean isResizable = true;
    private boolean isDoubleBuffered = false;
    private boolean isFocusable = true;
    private boolean isOpaque = true;

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

    public WindowBuilder setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        return this;
    }

    public WindowBuilder setAppImageFilePath(String appImageFilePath) {
        this.appImageFilePath = appImageFilePath;
        return this;
    }

    public WindowBuilder setLayout(LayoutManager layout) {
        this.layout = layout;
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
        return new Window(width, height, xCoord, yCoord, title, backgroundColor, isVisible, appImageFilePath, layout, isResizable, isDoubleBuffered, isFocusable, isOpaque);
    }

}
