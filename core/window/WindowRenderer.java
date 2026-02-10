package core.window;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class WindowRenderer {

    private final JFrame windowFrame;
    private final JLayeredPane windowPanel;

    public WindowRenderer() {
        windowFrame = new JFrame();
        windowPanel = new JLayeredPane();
    }
    
    public void render(Window window) {
        if (window.isDirty()) {
            window.updateSnapshot();

            WindowState snapshot = window.getSnapshot();
            SwingUtilities.invokeLater(() -> {
                System.out.println("Hello World!");
                windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                windowFrame.setResizable(window.isManuallyResizable());
                windowFrame.setTitle(window.getTitle());
                windowFrame.setIconImage(getIconImage(window.getAppImageFilePath()));

                windowPanel.setLayout(window.getLayout());
                windowPanel.setPreferredSize(new Dimension(window.getWidth(), window.getHeight()));

                windowPanel.setOpaque(window.isOpaque());
                windowPanel.setBackground(window.getBackgroundColor());

                windowPanel.setDoubleBuffered(window.isDoubleBuffered());
                windowPanel.setFocusable(window.isFocusable());

                windowFrame.add(windowPanel);
                windowFrame.pack();

                windowFrame.setLocation(window.getXCoord(), window.getYCoord());
                windowFrame.setVisible(window.isVisible());
            });
        }
    }

    public void addListener(WindowEventHandler windowEventHandler) {
        windowFrame.addWindowListener(windowEventHandler);
        windowFrame.addComponentListener(windowEventHandler);
    }

    private static BufferedImage getIconImage(String appImageFilePath) {
        try (var resource = WindowRenderer.class.getClassLoader().getResourceAsStream(appImageFilePath)) {
            if (resource != null) {
                BufferedImage appImage = ImageIO.read(resource);
                if (appImage != null) 
                    return appImage;
            }
        } catch (IOException e) {
            System.err.println("Failed to load appImage as resource: " + e.getMessage());
        }
        try {
            File appImageFile = new File(appImageFilePath);
            if (appImageFile.exists())
                return ImageIO.read(appImageFile);
        } catch (IOException e) {
            System.err.println("Failed to load appImage from file: " + e.getMessage());
        }
        System.err.println("Failed to load appImage from: " + appImageFilePath);
        return null;
    }

}
