package core.window;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Window {
    
    private final int width;
    private final int height;

    private final int xCoord;
    private final int yCoord;

    private final Color backgroundColor;

    private final String title;

    private final ImageIcon appImage;
    private final String appImageFilePath;

    private final boolean isResizable;
    private final boolean isDoubleBuffered;
    private final boolean isFocusable;
    private final boolean isOpaque;
    private final boolean isVisible;
    
    public Window(int width, int height, int xCoord, int yCoord, Color backgroundColor, String title, ImageIcon appImage, String appImageFilePath, boolean isResizable, boolean isDoubleBuffered, boolean isFocusable, boolean isOpaque, boolean isVisible) {
        this.width = width;
        this.height = height;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.backgroundColor = backgroundColor;
        this.title = title;
        this.appImage = appImage;
        this.appImageFilePath = appImageFilePath;
        this.isResizable = isResizable;
        this.isDoubleBuffered = isDoubleBuffered;
        this.isFocusable = isFocusable;
        this.isOpaque = isOpaque;
        this.isVisible = isVisible;
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

    public String getTitle() {
        return title;
    }

    public ImageIcon getAppImage() {
        return appImage;
    }

    public String getAppImageFilePath() {
        return appImageFilePath;
    }

    public boolean isResizable() {
        return isResizable;
    }

    public boolean isDoubleBuffered() {
        return isDoubleBuffered;
    }

    public boolean isFocusable() {
        return isFocusable;
    }

    public boolean isOpaque() {
        return isOpaque;
    }

    public boolean isVisible() {
        return isVisible;
    }

}
