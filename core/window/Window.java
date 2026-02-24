package core.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import core.helper.Loader;

public class Window {
    
    private final JFrame windowFrame;
    private final JLayeredPane windowPanel;

    private final String appImageFilePath;

    public Window(int width, int height, int xCoord, int yCoord, Color backgroundColor, boolean isFocusable, String title, String appImageFilePath, LayoutManager windowLayout, boolean isManuallyResizable, boolean isDoubleBuffered, boolean isOpaque, boolean isDecorated) {
        this.windowFrame = new JFrame();
        this.windowPanel = new JLayeredPane();
        this.appImageFilePath = appImageFilePath;

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

    public void init() {
        windowFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        windowFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                windowFrame, 
                "Do you want to save before exiting?", 
                "Confirm Exit", 
                JOptionPane.YES_NO_CANCEL_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                handleShutdown(); // Run cleanup and exit
            } else if (confirm == JOptionPane.NO_OPTION) {
                handleShutdown(); // Just exit
            }
            }
        });
    }

    public void addShutdownHook() {

    }

    public void addListener() {

    }

    public void addComponent() {

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

    private void handleShutdown() {
        System.out.println("Shutdown Hooks initiated:");
        
        //Implement system responsible for handling shutdown hooks
        
        windowFrame.dispose();
        System.exit(0);
    }

    private BufferedImage loadIconImage(String appImageFilePath) {
        return (appImageFilePath != null) ? Loader.loadImage(appImageFilePath) : Loader.loadImage("");
    }

    private void handleResizing() {
        windowFrame.revalidate();
        windowFrame.pack();
    }

    public void setWidth(int width) {
        int currentHeight = (int) windowPanel.getPreferredSize().getHeight();
        windowPanel.setPreferredSize(new Dimension(width, currentHeight));
        handleResizing();
    }

    public void setHeight(int height) {
        int currentWidth = (int) windowPanel.getPreferredSize().getWidth();
        windowPanel.setPreferredSize(new Dimension(currentWidth, height));
        handleResizing();
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
        return windowFrame != null && windowFrame.isShowing();
    }

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
