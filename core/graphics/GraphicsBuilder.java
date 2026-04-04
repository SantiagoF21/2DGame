package core.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import core.env.DisplayEnvironment;
import core.window.Window;

public class GraphicsBuilder {
    
    /* --- Defaults --- */

    private int width = 320;
    private int height = 180;

    private int xCoord = 0;
    private int yCoord = 0;

    private Color backgroundColor = Color.WHITE;

    private boolean isFocusable = true;

    private String name = "New Canvas";

    private int numOfBuffers = 3;

    private List<BufferedImage> images;

    private Color brushColor = new Color(0xFF, 0x8C, 0x40);

    /* --- Sizing --- */

    public GraphicsBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public GraphicsBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public GraphicsBuilder setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public GraphicsBuilder setSize(Dimension dimension) {
        this.width = dimension.width;
        this.height = dimension.height;
        return this;
    }

    /* --- Coordinates --- */

    public GraphicsBuilder setXCoord(int xCoord) {
        this.xCoord = xCoord;
        return this;
    }

    public GraphicsBuilder setYCoord(int yCoord) {
        this.yCoord = yCoord;
        return this;
    }

    public GraphicsBuilder setCoords(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        return this;
    }

    public GraphicsBuilder setCoords(Point coords) {
        this.xCoord = coords.x;
        this.yCoord = coords.y;
        return this;
    }

    public GraphicsBuilder center() {
        this.xCoord = (DisplayEnvironment.SCREEN_WIDTH - width) / 2;
        this.yCoord = (DisplayEnvironment.SCREEN_HEIGHT - height) / 2;
        return this;
    }

    public GraphicsBuilder center(Window window) {
        this.xCoord = (window.getWidth() - width) / 2;
        this.yCoord = (window.getHeight() - height) / 2;
        return this;
    }

    /* --- Appearance --- */

    public GraphicsBuilder setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public GraphicsBuilder setBackgroundColor(int red, int green, int blue) {
        this.backgroundColor = new Color(red, green, blue);
        return this;
    }

    public GraphicsBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public GraphicsBuilder setImages(List<BufferedImage> images) {
        this.images = images;
        return this;
    }

    /* --- Behavior --- */

    public GraphicsBuilder setFocusable(boolean isFocusable) {
        this.isFocusable = isFocusable;
        return this;
    }

    public GraphicsBuilder setNumOfBuffers(int numOfBuffers) {
        this.numOfBuffers = numOfBuffers;
        return this;
    }

    public GraphicsBuilder setBrushColor(Color brushColor) {
        this.brushColor = brushColor;
        return this;
    }

    public GraphicsBuilder setBrushColor(int red, int green, int blue) {
        this.brushColor = new Color(red, green, blue);
        return this;
    }

    /* --- Validation & Building --- */

    public void validate() {
        if (width <= 0) {
            throw new IllegalStateException("Width must be positive number; Width: " + width);
        } if (height <= 0) {
            throw new IllegalStateException("Height must be positive number; Height: " + height);
        } if (backgroundColor == null) {
            throw new IllegalStateException("Background Color cannot be null.");
        } if (numOfBuffers <= 0) {
            throw new IllegalStateException("The Number of Buffers must be positive number; Number of Buffers: " + numOfBuffers);
        }
    }

    public Graphics build() {
        validate();
        return new Graphics(width, height, xCoord, yCoord, backgroundColor, isFocusable, name, numOfBuffers, images, brushColor);
    }

}
