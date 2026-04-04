package app;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JOptionPane;

import core.graphics.Graphics;
import core.graphics.GraphicsBuilder;
import core.input.Keyboard;
import core.input.Mouse;
import core.window.Window;
import core.window.WindowBuilder;

public class Main {

    public static void main(String[] args) {

        /* Window */

        Window window = new WindowBuilder().setShutdown(() -> {
            int choice = JOptionPane.showConfirmDialog(
            null, 
            "Are you sure you want to quit?", 
            "Quit Application", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

            if (choice == JOptionPane.YES_OPTION) {
                System.out.println("Cleaning up resources...");
                System.exit(0);
            }
        }).setManuallyResizable(false).build();
        window.init();
        window.create();

        /* Graphics */


        Graphics graphics = new GraphicsBuilder().center(window).setBackgroundColor(new Color(0x53, 0xB7, 0x81)).build();

        window.addGraphics(graphics);

        graphics.create();
        graphics.init();

        graphics.brushThickness = 5;
        graphics.brushColor = new Color(0xFF, 0xFF, 0xFF);

        Keyboard keyboard = new Keyboard();
        Mouse mouse = new Mouse();

        

        window.addKeyboard(keyboard);
        window.addMouse(mouse);        

        long lastDebugTime = System.currentTimeMillis();

        int count = 0;

        // This creates a race condition, look into it

        int x1 = 100;
        int y1 = 50;

        int x2 = 200;
        int y2 = 50;

        int x3 = 150;
        int y3 = 150;

        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastDebugTime >= 1500) {
                count++;
                lastDebugTime = currentTime;
                Random random = new Random();
                int rWindow = random.nextInt(256);
                int gWindow = random.nextInt(256);
                int bWindow = random.nextInt(256);
                window.setBackgroundColor(new Color(rWindow, gWindow, bWindow));

                int rGraphics = random.nextInt(256);
                int gGraphics = random.nextInt(256);
                int bGraphics = random.nextInt(256);
                if (count >= 5) {
                    graphics.close();
                } else {
                    BufferStrategy bs = graphics.getDrawingCanvas().getBufferStrategy();

                    Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
                    graphics.setBackgroundColor(g2, new Color(rGraphics, gGraphics, bGraphics));
                    graphics.drawTriangle(g2, new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));

                    g2.dispose();
                    bs.show();
                }

                System.out.println(window);
            }
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void clear() {
        
    }

    public void render() {
        /* 
        BufferStrategy bs = drawingCanvas.getBufferStrategy();
        if (bs == null) {
            drawingCanvas.createBufferStrategy(numOfBuffers); 
            return;
        }

        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();

        g2d.setColor(drawingCanvas.getBackground());
        g2d.fillRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

        g2d.setColor(Color.GREEN);
        g2d.drawString("Engine Rendering Active", 20, 20);

        drawTriangle(bs, g2d, 60, 60, 20, Color.BLUE);

        g2d.dispose();
        bs.show();
        */
    }

    public void swapBuffer() {

    }

}
