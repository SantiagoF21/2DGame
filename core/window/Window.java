package core.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import core.helper.Loader;

public class Window {
    
    private JFrame windowFrame;
    private JLayeredPane windowPanel;

    protected Window(int width, int height, int xCoord, int yCoord, Color backgroundColor, boolean isFocusable, String title, String appImageFilePath, LayoutManager windowLayout, boolean isManuallyResizable, boolean isDoubleBuffered, boolean isOpaque, boolean isDecorated) {
        windowFrame = new JFrame();
        windowPanel = new JLayeredPane();

        windowFrame.setResizable(isManuallyResizable);
        windowFrame.setLocation(xCoord, yCoord);
        windowFrame.setTitle(title);
        windowFrame.setUndecorated(!isDecorated);

        windowPanel.setPreferredSize(new Dimension(width, height));
        windowPanel.setLayout(windowLayout);
        windowPanel.setBackground(backgroundColor);
        windowPanel.setOpaque(isOpaque);
        windowPanel.setDoubleBuffered(isDoubleBuffered);
        windowPanel.setFocusable(isFocusable);

        if (appImageFilePath != null) {
            BufferedImage icon = Loader.loadImage(appImageFilePath);
            if (icon != null) {
                this.windowFrame.setIconImage(icon);
            }
        }
    }

    public void init() {
        windowFrame.add(windowPanel);

        windowFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        windowFrame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
            handleShutdown();
        }
    });
    }

    private void handleShutdown() {
        System.out.println("Shutdown Hooks initiated:");
        
        //Implement system responsible for handling shutdown hooks
        
        windowFrame.dispose();
        System.exit(0);
    }

    public void create() {
        SwingUtilities.invokeLater(() -> {
            windowFrame.pack();

            windowFrame.setLocation(getXCoord(), getYCoord());

            windowFrame.setVisible(true); 

            windowFrame.setLocation(getXCoord(), getYCoord());
            
            int targetState = isMinimized() ? JFrame.ICONIFIED : JFrame.NORMAL;
            windowFrame.setExtendedState(targetState);

            windowPanel.requestFocusInWindow();
        });
    }

    public void setWidth(int width) {
        int currentHeight = (int) windowPanel.getPreferredSize().getHeight();
        windowPanel.setPreferredSize(new Dimension(width, currentHeight));
        windowFrame.revalidate();
        windowFrame.pack();
    }

    public void setHeight(int height) {
        int currentWidth = (int) windowPanel.getPreferredSize().getWidth();
        windowPanel.setPreferredSize(new Dimension(currentWidth, height));
        windowFrame.revalidate();
        windowFrame.pack();
    }

    public void setXCoord(int xCoord) {
        windowFrame.setLocation(xCoord, windowFrame.getY());
    }

    public void setYCoord(int yCoord) {
        windowFrame.setLocation(windowFrame.getX(), yCoord);
    }

    public void setBackgroundColor(Color backgroundColor) {
        windowPanel.setBackground(backgroundColor);
    }

    public void setVisible(boolean isVisible) {
        windowFrame.setVisible(isVisible);
    }

    public void setMinimized(boolean isMinimized) {
        int state = isMinimized ? JFrame.ICONIFIED : JFrame.NORMAL;
        windowFrame.setExtendedState(state);
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

    public boolean isVisible() {
        return windowFrame.isVisible();
    }

    public boolean isMinimized() {
        return (windowFrame.getExtendedState() & JFrame.ICONIFIED) != 0;
    }

    public boolean isFocusable() {
        return windowPanel.isFocusable();
    }

    public boolean hasFocus() {
        return windowFrame.hasFocus();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getWidth();
        result = prime * result + getHeight();
        result = prime * result + getXCoord();
        result = prime * result + getYCoord();
        
        result = prime * result + (isVisible() ? 1231 : 1237);
        result = prime * result + (isMinimized() ? 1231 : 1237);
        result = prime * result + (isFocusable() ? 1231 : 1237);
        result = prime * result + (hasFocus() ? 1231 : 1237);
        
        result = prime * result + ((getBackgroundColor() == null) ? 0 : getBackgroundColor().hashCode());
        result = prime * result + ((windowFrame.getTitle() == null) ? 0 : windowFrame.getTitle().hashCode());
        result = prime * result + ((windowPanel.getLayout() == null) ? 0 : windowPanel.getLayout().hashCode());
        
        result = prime * result + (windowFrame.isResizable() ? 1231 : 1237);
        result = prime * result + (windowPanel.isDoubleBuffered() ? 1231 : 1237);
        result = prime * result + (windowPanel.isOpaque() ? 1231 : 1237);
        return result;
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
            isVisible() == other.isVisible() &&
            isMinimized() == other.isMinimized() &&
            isFocusable() == other.isFocusable() &&
            hasFocus() == other.hasFocus() &&
            windowFrame.isResizable() == other.windowFrame.isResizable() &&
            windowPanel.isDoubleBuffered() == other.windowPanel.isDoubleBuffered() &&
            windowPanel.isOpaque() == other.windowPanel.isOpaque() &&
            java.util.Objects.equals(getBackgroundColor(), other.getBackgroundColor()) &&
            java.util.Objects.equals(windowFrame.getTitle(), other.windowFrame.getTitle()) &&
            java.util.Objects.equals(windowPanel.getLayout(), other.windowPanel.getLayout());
    }

    @Override
    public String toString() {
        return "Window [" +
           "width=" + getWidth() + 
           ", height=" + getHeight() + 
           ", xCoord=" + getXCoord() + 
           ", yCoord=" + getYCoord() +
           ", backgroundColor=" + getBackgroundColor() + 
           ", isVisible=" + isVisible() + 
           ", isMinimized=" + isMinimized() +
           ", isFocusable=" + isFocusable() + 
           ", hasFocus=" + hasFocus() + 
           ", title=" + windowFrame.getTitle() + 
           ", layout=" + windowPanel.getLayout() + 
           ", isManuallyResizable=" + windowFrame.isResizable() +
           ", isDoubleBuffered=" + windowPanel.isDoubleBuffered() + 
           ", isOpaque=" + windowPanel.isOpaque() + 
           "]";
    }

}
