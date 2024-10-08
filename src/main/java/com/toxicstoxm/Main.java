package com.toxicstoxm;


import com.toxicstoxm.MazeGen.MazeGen;
import com.toxicstoxm.Ui.Window;


public class Main {

    public static final int width = 2560;
    public static final int height = 1440;

    public static final int scale = 1;

    public static final int scaled_width = width / scale;
    public static final int scaled_height = height / scale;


    public Main() {
        Window window = new Window(width, height);
        window.setResizable(false);

        MazeGen gen = new MazeGen(width, height, window);
        window.setVisible(true);

        gen.generateMaze();
    }

    public static void main(String[] args) {
        new Main();
    }
}