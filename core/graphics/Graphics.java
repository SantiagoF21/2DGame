package core.graphics;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.input.Keyboard;
import core.input.Mouse;

public class Graphics {

    private final Canvas drawingCanvas;

    private final Map<String, Component> componentMap;

    private final List<Keyboard> keyboards;
    private final List<Mouse> mice;

    private final List<BufferedImage> images;

    public Color brushColor;

    public int brushThickness;

    private final int numOfBuffers;

    public Graphics(int width, int height, int xCoord, int yCoord, Color backgroundColor, boolean isFocusable, String name, int numOfBuffers, List<BufferedImage> images, Color brushColor) {
        this.drawingCanvas = new Canvas();
        this.componentMap = new HashMap<>();
        this.keyboards = new ArrayList<>();
        this.mice = new ArrayList<>();
        this.numOfBuffers = numOfBuffers;
        this.images = new ArrayList<>();
        this.brushColor = brushColor;

        drawingCanvas.setSize(width, height);
        drawingCanvas.setLocation(xCoord, yCoord);
        drawingCanvas.setBackground(backgroundColor);
        drawingCanvas.setFocusable(isFocusable);
        drawingCanvas.setName(name);
    }

    /* --- Graphics' Lifecycle & Control --- */

    public void init() {
        drawingCanvas.setIgnoreRepaint(true);
        drawingCanvas.createBufferStrategy(numOfBuffers);
    }

    public void create() {
        drawingCanvas.setVisible(true);
    }

    public void close() {
        drawingCanvas.setVisible(false);
        drawingCanvas.setIgnoreRepaint(false);

        for (Keyboard keyboard : keyboards) {
            drawingCanvas.removeKeyListener(keyboard);
        }
        for (Mouse mouse : mice) {
            drawingCanvas.removeMouseListener(mouse);
            drawingCanvas.removeMouseMotionListener(mouse);
        }

        keyboards.clear();
        mice.clear();

        if (drawingCanvas.getBufferStrategy() != null) {
            drawingCanvas.getBufferStrategy().dispose();
        }
    }

    public void setBackgroundColor(int red, int green, int blue) {
        //drawingCanvas.setBackground(new Color(red, green, blue));
        BufferStrategy bs = drawingCanvas.getBufferStrategy();

        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();

        // This is what actually paints the background color
        g2d.setColor(new Color(red, green, blue));
        g2d.fillRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

        g2d.dispose();
        bs.show();
    }

    public void setBackgroundColor(Graphics2D g2d, Color backgroundColor) {
        // This is what actually paints the background color
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
    }

    public Canvas getDrawingCanvas() {
        return drawingCanvas;
    }

    /* --- Component & Graphic Management --- */

    public void drawPoint(int xCoord, int yCoord, int thickness) {

    }

    public void drawPoint(Point point, int thickness) {

    }

    public void drawLine(Graphics2D g2d, int xCoord1, int yCoord1, int xCoord2, int yCoord2) {
        g2d.setColor(new Color(0xFF, 0xFF, 0xFF));
        g2d.setStroke(new BasicStroke(5)); //Idk let's go Back to this
        g2d.drawLine(xCoord1, yCoord1, xCoord2, yCoord2);
    }

    public void drawLine(Graphics2D g2d, Point point1, Point point2) {
        drawLine(g2d, point1.x, point1.y, point2.x, point2.y);
    }

    public void drawCircle() {
        Graphics2D brush = (Graphics2D) drawingCanvas.getGraphics();
        
    }

    public void drawSquare() {

    }

    public void drawRectangle() {

    }

    public void drawTriangle(Graphics2D g2d, Point p1, Point p2, Point p3) {
        g2d.setColor(new Color(0xFF, 0xFF, 0xFF));
        g2d.setStroke(new BasicStroke(10)); //Idk let's go Back to this
        int[] xPoints = { p1.x, p2.x, p3.x };
        int[] yPoints = { p1.y, p2.y, p3.y };

        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    public void drawRhombus() {

    }

    public void drawPolygon() {

    }

    public void drawImage() {

    }

    /* --- Component & Graphic Management --- */



    /* --- Input Management --- */

    /* --- Setters --- */

    /* --- Getters --- */

    /* --- Helpers --- */

    /* --- Overrides --- */

}
