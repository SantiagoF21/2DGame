package core;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private final List<Layer> layerStack = new ArrayList<>();
    private boolean running = true;

    public void pushLayer(Layer layer) {
        layerStack.add(layer);
    }

    public void run() {
        while (running) {
            //float dt = calculateDeltaTime();
            
            // The Core calls the App's code (Inversion of Control)
            //for (Layer layer : layerStack) {
            //    layer.onUpdate(dt);
            //    layer.onRender();
            //}
            
            //updateWindow();
        }
    }
}
