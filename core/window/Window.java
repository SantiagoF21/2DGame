package core.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import core.graphics.Graphics;
import core.helper.Loader;
import core.input.Keyboard;
import core.input.Mouse;

public class Window {
    
    private final JFrame windowFrame;
    private final JLayeredPane windowPanel;

    private final Map<String, Component> componentMap;

    private final List<Keyboard> keyboards;
    private final List<Mouse> mice;

    private final String appImageFilePath;

    private final Shutdown shutdown;

    public Window(int width, int height, int xCoord, int yCoord, Color backgroundColor, boolean isFocusable, String title, String appImageFilePath, LayoutManager windowLayout, boolean isManuallyResizable, boolean isDoubleBuffered, boolean isOpaque, boolean isDecorated, Shutdown shutdown) {
        this.windowFrame = new JFrame();
        this.windowPanel = new JLayeredPane();
        this.componentMap = new HashMap<>();
        this.keyboards = new ArrayList<>();
        this.mice = new ArrayList<>();
        this.appImageFilePath = appImageFilePath;
        this.shutdown = shutdown;

        windowFrame.setLocation(xCoord, yCoord);
        windowFrame.setResizable(isManuallyResizable);
        windowFrame.setTitle(title);
        windowFrame.setIconImage(loadIconImage(appImageFilePath));
        windowFrame.setUndecorated(!isDecorated);

        windowPanel.setPreferredSize(new Dimension(width, height));
        windowPanel.setLayout(windowLayout);
        windowPanel.setDoubleBuffered(isDoubleBuffered);
        windowPanel.setOpaque(isOpaque);
        windowPanel.setBackground(backgroundColor);
        windowPanel.setFocusable(isFocusable);
        
        windowFrame.add(windowPanel);
    }

    /* --- Window's Lifecycle & Control --- */

    public void init() {
        windowFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        windowFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (shutdown != null) {
                    shutdown.execute();
                }
            }
        });
    }

    public void create() {
        SwingUtilities.invokeLater(() -> {
            windowFrame.pack();
            windowFrame.setLocation(getXCoord(), getYCoord());

            windowFrame.setVisible(true); 
            
            int targetState = isMinimized() ? JFrame.ICONIFIED : JFrame.NORMAL;
            windowFrame.setExtendedState(targetState);

            windowPanel.requestFocusInWindow();
        });
    }

    public void close() {
        windowFrame.dispose();
    }

    /* --- Component & Graphic Management --- */

    public void addComponent(Component component) {
        windowPanel.add(component);
        addToMap(component);
        sync();
    }

    public void addComponent(Component component, Integer layer) {
        windowPanel.add(component, layer);
        addToMap(component);
        sync();
    }

    public void addComponents(List<Component> components) {
        components.forEach(this::addComponentWithoutSync);
        sync();
    }

    public void addGraphics(Graphics graphics) {
        addComponent(graphics.getDrawingCanvas());
    }

    public void addGraphics(Graphics graphics, Integer layer) {
        addComponent(graphics.getDrawingCanvas(), layer);
    }

    public void addGraphics(List<Graphics> graphics) {
        graphics.forEach(this::addGraphicsWithoutSync);
        sync();
    }

    public void removeComponent(Component component) {
        if (component == null)
            return;
        if (component.getParent() != windowPanel) {
            System.err.println("Warning: component '" + component.getName() + "' is not a child of this window's panel.");
        return;
        }
        windowPanel.remove(component);
        componentMap.remove(component.getName());
        sync();
    }

    public void removeGraphics(Graphics graphics) {
        if (graphics != null) {
            removeComponent(graphics.getDrawingCanvas());
        }
    }

    public void clear() {
        windowPanel.removeAll();
        componentMap.clear();
        sync();
    }

    public void sync() {
        windowPanel.revalidate();
        windowFrame.repaint();
    }

    public Component getComponentByName(String name) {
        return componentMap.get(name);
}

    public Component[] getComponents() {
        return windowPanel.getComponents();
    }

    public int getComponentCount() {
        return windowPanel.getComponentCount();
    }

    /* --- Input Management --- */

    public void addMouse(Mouse mouse) {
        windowPanel.addMouseListener(mouse);
        windowPanel.addMouseMotionListener(mouse);
        windowPanel.addMouseWheelListener(mouse);
        mice.add(mouse);
    }

    public void removeMouse(Mouse mouse) {
        windowPanel.removeMouseListener(mouse);
        windowPanel.removeMouseMotionListener(mouse);
        windowPanel.removeMouseWheelListener(mouse);
        mice.remove(mouse);
    }

    public List<Mouse> getMice() {
        return Collections.unmodifiableList(mice);
    }

    public void addKeyboard(Keyboard keyboard) {
        windowPanel.addKeyListener(keyboard);
        windowFrame.addKeyListener(keyboard);
        keyboards.add(keyboard);
    }

    public void removeKeyboard(Keyboard keyboard) {
        windowPanel.removeKeyListener(keyboard);
        windowFrame.removeKeyListener(keyboard);
        keyboards.remove(keyboard);
    }

    public List<Keyboard> getKeyboards() {
        return Collections.unmodifiableList(keyboards);
    }

    /* --- Setters --- */

    public void setWidth(int width) {
        int currentHeight = (int) windowPanel.getPreferredSize().getHeight();
        windowPanel.setPreferredSize(new Dimension(width, currentHeight));
        applyResize();
    }

    public void setHeight(int height) {
        int currentWidth = (int) windowPanel.getPreferredSize().getWidth();
        windowPanel.setPreferredSize(new Dimension(currentWidth, height));
        applyResize();
    }

    public void setXCoord(int xCoord) {
        windowFrame.setLocation(xCoord, windowFrame.getY());
    }

    public void setYCoord(int yCoord) {
        windowFrame.setLocation(windowFrame.getX(), yCoord);
    }

    public void setBackgroundColor(Color backgroundColor) {
        windowPanel.setBackground(backgroundColor);
        windowPanel.repaint();
    }

    public void setFocusable(boolean isFocusable) {
        windowPanel.setFocusable(isFocusable);
    }

    /* --- Getters --- */

    public int getWidth() {
        return (windowPanel.getWidth() > 0) ? windowPanel.getWidth() : windowPanel.getPreferredSize().width;
    }

    public int getHeight() {
        return (windowPanel.getHeight() > 0) ? windowPanel.getHeight() : windowPanel.getPreferredSize().height;
    }

    public int getXCoord() {
        return windowFrame.getX();
    }

    public int getYCoord() {
        return windowFrame.getY();
    }

    public Color getBackgroundColor() {
        return windowPanel.getBackground();
    }

    public boolean isFocusable() {
        return windowPanel.isFocusable();
    }

    public String getTitle() {
        return windowFrame.getTitle();
    }

    public String getAppImageFilePath() {
        return appImageFilePath;
    }

    public LayoutManager getWindowLayout() {
        return windowPanel.getLayout();
    }

    public boolean isManuallyResizable() {
        return windowFrame.isResizable();
    }

    public boolean isDoubleBuffered() {
        return windowPanel.isDoubleBuffered();
    }

    public boolean isOpaque() {
        return windowPanel.isOpaque();
    }

    public boolean isDecorated() {
        return !windowFrame.isUndecorated();
    }

    public boolean isVisible() {
        return windowFrame.isVisible();
    }

    public boolean isMinimized() {
        return (windowFrame.getExtendedState() & JFrame.ICONIFIED) != 0;
    }

    public boolean hasFocus() {
        return windowFrame.hasFocus();
    }

    public boolean isShowing() {
        return windowFrame.isShowing();
    }

    /* --- Helpers --- */

    private void addToMap(Component component) {
        if (component.getName() != null) {
            componentMap.put(component.getName(), component);
        }
    }

    private void applyResize() {
        final boolean DIMENSION_IS_POSITIVE = windowPanel.getPreferredSize().width > 0 && windowPanel.getPreferredSize().height > 0;
        if (DIMENSION_IS_POSITIVE) {
            windowFrame.revalidate();
            windowFrame.pack();
        }
    }

    private void addComponentWithoutSync(Component component) {
        windowPanel.add(component);
        addToMap(component);
    }

    private void addGraphicsWithoutSync(Graphics graphics) {
        addComponentWithoutSync(graphics.getDrawingCanvas());
    }

    private BufferedImage loadIconImage(String appImageFilePath) {
        return (appImageFilePath != null) ? Loader.loadImage(appImageFilePath) : null;
    }

    /* --- Overrides --- */

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getWidth(), getHeight(), getXCoord(), getYCoord(), getBackgroundColor(), isManuallyResizable());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Window other = (Window) obj;

        return getWidth() == other.getWidth() &&
           getHeight() == other.getHeight() &&
           getXCoord() == other.getXCoord() &&
           getYCoord() == other.getYCoord() &&
           isManuallyResizable() == other.isManuallyResizable() &&
           Objects.equals(getTitle(), other.getTitle()) &&
           Objects.equals(getBackgroundColor(), other.getBackgroundColor());
    }

    @Override
    public String toString() {
        return "Window [" +
           "\n  Title: " + getTitle() +
           "\n  Dimensions: " + getWidth() + "x" + getHeight() +
           "\n  Coordinates: (" + getXCoord() + ", " + getYCoord() + ")" +
           "\n  Visibility: [Visible: " + isVisible() + ", Minimized: " + isMinimized() + "]" +
           "\n  Interaction: [Focusable: " + isFocusable() + ", Has Focus: " + hasFocus() + "]" +
           "\n  Appearance: [Opaque: " + isOpaque() + ", Decorated: " + isDecorated() + ", Resizable: " + isManuallyResizable() + "]" +
           "\n  Background: " + getBackgroundColor() +
           "\n  Layout: " + getWindowLayout() +
           "\n]";
    }

}
