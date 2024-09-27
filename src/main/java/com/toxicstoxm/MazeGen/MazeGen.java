package com.toxicstoxm.MazeGen;

import com.toxicstoxm.Main;
import com.toxicstoxm.Ui.BoolCanvas;
import com.toxicstoxm.Ui.Window;

public class MazeGen {

    public static final int FPS = 60;
    public static final int MSPF = 1000 / FPS;
    public static long startOfFrame = System.currentTimeMillis();

    private final BoolCanvas canvas;
    private final Window window;

    public MazeGen(int width, int height, Window window) {
        canvas = new BoolCanvas(width, height);
        window.add(canvas);
        this.window = window;
    }

    public void generateMaze() {
        boolean toggle = true;
        while (true) {
            startOfFrame = System.currentTimeMillis();
            for (int y = 0; y < Main.scaled_height; y++) {
                for (int x = 0; x < Main.scaled_width; x++) {
                    if (toggle) {
                        canvas.enable(x * Main.scale, y * Main.scale);
                    } else canvas.disable(x * Main.scale, y * Main.scale);
                    toggle = !toggle;
                }
                toggle = !toggle;
            }
            canvas.repaint();
            toggle = !toggle;

            try {
                Thread.sleep(MSPF);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
