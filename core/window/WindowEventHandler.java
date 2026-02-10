package core.window;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class WindowEventHandler extends WindowAdapter implements ComponentListener {

    private final Window model;
    private final WindowRenderer view;
    private final Runnable onShutDown;   

    public WindowEventHandler(Window model, WindowRenderer view, Runnable onShutDown) {
        this.model = model;
        this.view = view;
        this.onShutDown = onShutDown;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (onShutDown != null) {
            onShutDown.run();
        }
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        model.setVisible(true);
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        model.setFocusable(true);
    }

    @Override
    public void componentHidden(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {
        model.setXCoord(e.getComponent().getX());
        model.setYCoord(e.getComponent().getY());
    }

    @Override
    public void componentResized(ComponentEvent e) {
        model.setWidth(e.getComponent().getWidth());
        model.setHeight(e.getComponent().getHeight());
    }

    @Override
    public void componentShown(ComponentEvent e) {}
    
}
