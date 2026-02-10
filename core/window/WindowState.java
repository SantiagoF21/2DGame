package core.window;

import java.awt.Color;

class WindowState {
    private int width;
    private int height;

    private int xCoord;
    private int yCoord;

    private Color backgroundColor;

    private boolean isVisible;
    private boolean isFocusable;

    void copyFrom(WindowState other) {
        this.width = other.width;
        this.height = other.height;
        this.xCoord = other.xCoord;
        this.yCoord = other.yCoord;
        this.backgroundColor = other.backgroundColor;
        this.isVisible = other.isVisible;
        this.isFocusable = other.isFocusable;
    }

    void copyFrom(Window other) {
        this.width = other.getWidth();
        this.height = other.getHeight();
        this.xCoord = other.getXCoord();
        this.yCoord = other.getYCoord();
        this.backgroundColor = other.getBackgroundColor();
        this.isVisible = other.isVisible();
        this.isFocusable = other.isFocusable();
    }
}
