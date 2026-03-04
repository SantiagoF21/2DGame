package core.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Graphics extends Canvas {

    private final int numOfBuffers;

    private final List<BufferedImage> images;

    public Graphics(int width, int height, int xCoord, int yCoord, Color backgroundColor, boolean isFocusable, int numOfBuffers, List<BufferedImage> images) {
        this.numOfBuffers = numOfBuffers;
        this.images = new ArrayList<>();

        setSize(width, height);
        setLocation(xCoord, yCoord);
        setBackground(backgroundColor);
        setFocusable(isFocusable);

        setIgnoreRepaint(true); 
    }

    public void clear() {

    }

    public void render() {
        // 1. Get the BufferStrategy (create it if it doesn't exist)
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            // Triple buffering for maximum smoothness
            createBufferStrategy(numOfBuffers); 
            return;
        }

        // 2. Get the graphics context from the buffer
        java.awt.Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        // --- DRAWING AREA START ---
        
        // Clear screen
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Place holder for your game objects:
        g2d.setColor(Color.GREEN);
        g2d.drawString("Engine Rendering Active", 20, 20);

        // --- DRAWING AREA END ---

        // 3. Dispose and show
        g2d.dispose();
        bs.show();
    }

}
