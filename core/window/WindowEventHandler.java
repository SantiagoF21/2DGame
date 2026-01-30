package core.window;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

final class WindowEventHandler extends WindowAdapter implements ComponentListener {

    private final Window model;
    private final WindowRenderer view;
    private final Runnable onShutDown;   

    public WindowEventHandler(Window model, WindowRenderer view, Runnable onShutDown) {
        this.model = model;
        this.view = view;
        this.onShutDown = onShutDown;
    }

    @Override
    public void windowActivated(WindowEvent e) {
        super.windowActivated(e);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
        super.windowClosed(e);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosed(e);
        if (onShutDown != null)
            onShutDown.run();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
        super.windowDeactivated(e);
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
    public void windowIconified(WindowEvent e) {
        model.setVisible(false);
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        // TODO Auto-generated method stub
        super.windowLostFocus(e);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        super.windowOpened(e);
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        // TODO Auto-generated method stub
        super.windowStateChanged(e);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        model.setWidth(e.getComponent().getWidth());
        model.setHeight(e.getComponent().getHeight());
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        model.setXCoord(e.getComponent().getX());
        model.setYCoord(e.getComponent().getY());
    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'componentShown'");
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'componentHidden'");
    }
    
}
