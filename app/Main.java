package app;

import core.window.Window;
import core.window.WindowBuilder;
import core.window.WindowRenderer;

public class Main {

    public static void main(String[] args) {
        Window testWindow = new WindowBuilder().build();
        WindowRenderer.render(testWindow);
    }

}
