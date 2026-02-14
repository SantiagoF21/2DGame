package core.window;

import java.awt.Color;

public class WindowMemento {

    private int width;
    private int height;

    private int xCoord;
    private int yCoord;

    private Color backgroundColor;

    private boolean isVisible;
    private boolean isMinimized;

    private boolean isFocusable;
    private boolean hasFocus;

    public WindowMemento(Window window) {
        copy(window);
    }

    void updateFrom(Window window) {
        copy(window);
    }

    private void copy(Window window) {
        this.width = window.getWidth();
        this.height = window.getHeight();
        this.xCoord = window.getXCoord();
        this.yCoord = window.getYCoord();
        this.backgroundColor = window.getBackgroundColor();
        this.isVisible = window.isVisible();
        this.isMinimized = window.isMinimized();
        this.isFocusable = window.isFocusable();
        this.hasFocus = window.hasFocus();
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

    public boolean isVisible() {
        return isVisible;
    }

    public boolean isMinimized() {
        return isMinimized;
    }

    public boolean isFocusable() {
        return isFocusable;
    }

    public boolean hasFocus() {
        return hasFocus;
    }

}
