package app;

import core.window.Window;
import core.window.WindowBuilder;
import core.window.WindowEventHandler;
import core.window.WindowRenderer;

public class Main {

    public static void main(String[] args) {
        Window testWindow = new WindowBuilder().build();
        WindowRenderer windowRenderer = new WindowRenderer();
        WindowEventHandler windowHandler = new WindowEventHandler(testWindow, windowRenderer, null);
        windowRenderer.addListener(windowHandler);
        windowRenderer.render(testWindow);
    }

}
