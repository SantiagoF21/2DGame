package core.window;

import java.awt.Color;
import java.awt.LayoutManager;

public class Window {
    
    private int width;
    private int height;

    private int xCoord;
    private int yCoord;

    private String title;
    private Color backgroundColor;

    private boolean isVisible;
    private boolean isFocusable;

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

    protected void setWidth(int width) {
        this.width = width;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    protected void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    protected void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    protected void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    protected void setBackgroundColor(int red, int green, int blue) {
        this.backgroundColor = new Color(red, green, blue);
    }

    protected void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    protected void setFocusable(boolean isFocusable) {
        this.isFocusable = isFocusable;
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

}
