package core.window;

import java.awt.Color;
import java.awt.LayoutManager;

public class Window {
    
    private int width;
    private int height;

    private int xCoord;
    private int yCoord;

    private Color backgroundColor;

    private boolean isVisible;
    private boolean isFocusable;

    /* Represents when a Window should redrawn on the EDT (Event Dispatch Thread) */
    private boolean isDirty;

    private final String title;
    private final String appImageFilePath;
    private final LayoutManager layout;

    private final boolean isManuallyResizable;
    private final boolean isDoubleBuffered;
    private final boolean isOpaque;

    protected Window(int width, int height, int xCoord, int yCoord, Color backgroundColor, boolean isVisible, boolean isFocusable, String title, String appImageFilePath, LayoutManager layout, boolean isManuallyResizable, boolean isDoubleBuffered, boolean isOpaque) {
        this.width = width;
        this.height = height;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.backgroundColor = backgroundColor;
        this.isVisible = isVisible;
        this.isFocusable = isFocusable;
        /* Window when instanciated will be treated as dirty so that it can render for the first time.*/
        this.isDirty = true;
        this.title = title;
        this.appImageFilePath = appImageFilePath;
        this.layout = layout;
        this.isManuallyResizable = isManuallyResizable;
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

    protected synchronized void setBounds(int xCoord, int yCoord, int width, int height) {
        if (this.xCoord != xCoord || this.yCoord != yCoord || this.width != width || this.height != height) {
            this.xCoord = xCoord;
            this.yCoord = yCoord;
            this.width = width;
            this.height = height;
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

    /* Should only be read/written/modified by WindowRenderer */
    protected synchronized void clean() {
        this.isDirty = false;
    }

    public synchronized int getWidth() {
        return width;
    }

    public synchronized int getHeight() {
        return height;
    }

    public synchronized int getXCoord() {
        return xCoord;
    }

    public synchronized int getYCoord() {
        return yCoord;
    }

    public synchronized String getTitle() {
        return title;
    }

    public synchronized Color getBackgroundColor() {
        return backgroundColor;
    }

    public synchronized boolean isVisible() {
        return isVisible;
    }

    public synchronized boolean isFocusable() {
        return isFocusable;
    }

    /* Should only be read/written/modified by WindowRenderer */
    protected synchronized boolean isDirty() {
        return isDirty;
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

    protected synchronized Window getSnapshot() {
        return new Window(width, height, xCoord, yCoord, backgroundColor, isVisible, isFocusable, title, appImageFilePath, layout, isManuallyResizable, isDoubleBuffered, isOpaque);
    }

}
