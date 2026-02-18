package core.window;

import java.awt.Color;
import java.awt.LayoutManager;

import core.env.DisplayEnvironment;

public class WindowBuilder {
    
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

    public WindowBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public WindowBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public WindowBuilder setXCoord(int xCoord) {
        this.xCoord = xCoord;
        return this;
    }

    public WindowBuilder setYCoord(int yCoord) {
        this.yCoord = yCoord;
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

    public WindowBuilder setFocusable(boolean isFocusable) {
        this.isFocusable = isFocusable;
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

    public WindowBuilder centerOnScreen() {
        this.xCoord = (DisplayEnvironment.SCREEN_WIDTH - width) / 2;
        this.yCoord = (DisplayEnvironment.SCREEN_HEIGHT - height) / 2;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isFocusable() {
        return isFocusable;
    }

    public String getTitle() {
        return title;
    }

    public String getAppImageFilePath() {
        return appImageFilePath;
    }

    public LayoutManager getLayout() {
        return layout;
    }

    public boolean isManuallyResizable() {
        return isManuallyResizable;
    }

    public boolean isDoubleBuffered() {
        return isDoubleBuffered;
    }

    public boolean isOpaque() {
        return isOpaque;
    }

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
        return new Window(width, height, xCoord, yCoord, backgroundColor, isFocusable, title, appImageFilePath, layout, isManuallyResizable, isDoubleBuffered, isOpaque);
    }

}
