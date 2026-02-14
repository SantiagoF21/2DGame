package core.window;

import java.awt.Color;
import java.awt.LayoutManager;

public class Window {

    /* CORE FIELDS */

    /* Flag that represents when a Window needs be rendered on the EDT (Event Dispatch Thread) */
    private boolean isDirty;

    /* Implements Memento Design Pattern so that snapshots can be renderered on the EDT; Clear separation of states; Design -> Window.java (Originator), WindowMemento.java (Memento) N/A (Caretaker) */
    private WindowMemento snapshot;

    /* APP FIELDS */

    private int width;
    private int height;

    private int xCoord;
    private int yCoord;

    private Color backgroundColor;

    private boolean isVisible;
    private boolean isMinimized;

    private boolean isFocusable;
    private boolean hasFocus;

    private final String title;
    private final String appImageFilePath;

    private final LayoutManager layout;
    private final boolean isManuallyResizable;

    private final boolean isDoubleBuffered;
    
    private final boolean isOpaque;

    /* Implements Builder Design Pattern so that devs can make Window instances without having to worry about argument order; Design -> Window.java (Product), WindowBuilder.java (Builder/ConcreteBuilder/Director), Custom Application (Client) */
    protected Window(int width, int height, int xCoord, int yCoord, Color backgroundColor, boolean isVisible, boolean isMinimized, boolean isFocusable, boolean hasFocus, String title, String appImageFilePath, LayoutManager layout, boolean isManuallyResizable, boolean isDoubleBuffered, boolean isOpaque) {
        this.width = width;
        this.height = height;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.backgroundColor = backgroundColor;
        this.isVisible = isVisible;
        this.isMinimized = isMinimized;
        this.isFocusable = isFocusable;
        this.hasFocus = hasFocus;
        this.title = title;
        this.appImageFilePath = appImageFilePath;
        this.layout = layout;
        this.isManuallyResizable = isManuallyResizable;
        this.isDoubleBuffered = isDoubleBuffered;
        this.isOpaque = isOpaque;
        markDirty();
        this.snapshot = new WindowMemento(this);
    }

    /* CORE METHODS */

    /* Should only be called when Window is instantiated or mutable field is changed */
    protected synchronized void markDirty() {
        this.isDirty = true;
    }

    /* Should only be called when Window is instantiated or mutable field is changed */
    protected synchronized void markClean() {
        this.isDirty = false;
    }

    /* If window is considered dirty, a snapshot of our window's mutable fields will be taken at that instance. */
    protected synchronized void updateSnapshot() {
        if (isDirty) {
            markClean();
            snapshot.updateFrom(this);
        }
    }

    /* Should only be called when Window is getting rendered */
    protected synchronized boolean isDirty() {
        return isDirty;
    }

    /* Returns an instance of our snapshot */
    protected synchronized WindowMemento getSnapshot() {
        return snapshot;
    }

    /* APP METHODS */

    public synchronized void setWidth(int width) {
        if (this.width != width) {
            this.width = width;
            markDirty();
        }
    }

    public synchronized void setHeight(int height) {
        if (this.height != height) {
            this.height = height;
            markDirty();
        }
    }

    public synchronized void setXCoord(int xCoord) {
        if (this.xCoord != xCoord) {
            this.xCoord = xCoord;
            markDirty();
        }
    }

    public synchronized void setYCoord(int yCoord) {
        if (this.yCoord != yCoord) {
            this.yCoord = yCoord;
            markDirty();
        }
    }

    public synchronized void setBackgroundColor(Color backgroundColor) {
        if (!this.backgroundColor.equals(backgroundColor)) {
            this.backgroundColor = backgroundColor;
            markDirty();
        }
    }

    public synchronized void setBackgroundColor(int red, int green, int blue) {
        if (this.backgroundColor.getRGB() != new Color(red, green, blue).getRGB()) {    
            this.backgroundColor = new Color(red, green, blue);
            markDirty();
        }
    }

    public synchronized void setVisible(boolean isVisible) {
        if (this.isVisible != isVisible) {    
            this.isVisible = isVisible;
            markDirty();
        }
    }

    public synchronized void setMinimized(boolean isMinimized) {
        if (this.isMinimized != isMinimized) {
            this.isMinimized = isMinimized;
            markDirty();
        }
    }

    public synchronized void setFocusable(boolean isFocusable) {
        if (this.isFocusable != isFocusable) {    
            this.isFocusable = isFocusable;
            markDirty();
        }
    }

    public synchronized void setHasFocus(boolean hasFocus) {
        if (this.hasFocus != hasFocus) {
            this.hasFocus = hasFocus;
            markDirty();
        }
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

    public synchronized Color getBackgroundColor() {
        return backgroundColor;
    }

    public synchronized boolean isVisible() {
        return isVisible;
    }

    public synchronized boolean isMinimized() {
        return isMinimized;
    }

    public synchronized boolean isFocusable() {
        return isFocusable;
    }

    public synchronized boolean hasFocus() {
        return hasFocus;
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

}
