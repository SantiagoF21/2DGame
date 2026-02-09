package core.window;

import java.awt.Color;
import java.awt.LayoutManager;

public class Window {

    private volatile boolean isDirty;
    
    private volatile int width;
    private volatile int height;

    private volatile int xCoord;
    private volatile int yCoord;

    private volatile String title;
    private volatile Color backgroundColor;

    private volatile boolean isVisible;
    private volatile boolean isFocusable;

    private final String appImageFilePath;
    private final LayoutManager layout;

    private final boolean isResizable;
    private final boolean isDoubleBuffered;
    private final boolean isOpaque;

    protected Window(int width, int height, int xCoord, int yCoord, String title, Color backgroundColor, boolean isVisible, boolean isFocusable, String appImageFilePath, LayoutManager layout, boolean isResizable, boolean isDoubleBuffered, boolean isOpaque) {
        this.width = width;
        this.height = height;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.isVisible = isVisible;
        this.isFocusable = isFocusable;
        this.appImageFilePath = appImageFilePath;
        this.layout = layout;
        this.isResizable = isResizable;
        this.isDoubleBuffered = isDoubleBuffered;
        this.isOpaque = isOpaque;
    }

    protected synchronized void setWidth(int width) {
        if (this.width != width) {
            this.width = width;
            this.isDirty = true;
        }
    }

    protected synchronized void setHeight(int height) {
        if (this.height != height) {
            this.height = height;
            this.isDirty = true;
        }
    }

    protected synchronized void setXCoord(int xCoord) {
        if (this.xCoord != xCoord) {
            this.xCoord = xCoord;
            this.isDirty = true;
        }
    }

    protected synchronized void setYCoord(int yCoord) {
        if (this.yCoord != yCoord) {
            this.yCoord = yCoord;
            this.isDirty = true;
        }
    }

    protected synchronized void setTitle(String title) {
        if (this.title != title) {
            this.title = title;
            this.isDirty = true;
        }
    }

    protected synchronized void setBackgroundColor(Color backgroundColor) {
        if (!this.backgroundColor.equals(backgroundColor)) {
            this.backgroundColor = backgroundColor;
            this.isDirty = true;
        }
    }

    protected synchronized void setBackgroundColor(int red, int green, int blue) {
        if (!this.backgroundColor.equals(new Color(red, green, blue))) {    
            this.backgroundColor = new Color(red, green, blue);
            this.isDirty = true;
        }
    }

    protected synchronized void setVisible(boolean isVisible) {
        if (this.isVisible != isVisible) {    
            this.isVisible = isVisible;
            this.isDirty = true;
        }
    }

    protected synchronized void setFocusable(boolean isFocusable) {
        if (this.isFocusable != isFocusable) {    
            this.isFocusable = isFocusable;
            this.isDirty = true;
        }
    }

    public synchronized void clean() {
        this.isDirty = false;
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

    public String getTitle() {
        return title;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public boolean isFocusable() {
        return isFocusable;
    }

    public String getAppImageFilePath() {
        return appImageFilePath;
    }

    public LayoutManager getLayout() {
        return layout;
    }

    public boolean isResizable() {
        return isResizable;
    }

    public boolean isDoubleBuffered() {
        return isDoubleBuffered;
    }

    public boolean isOpaque() {
        return isOpaque;
    }

    public boolean isDirty() {
        return isDirty;
    }

}
