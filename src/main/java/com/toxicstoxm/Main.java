package com.toxicstoxm;


import com.toxicstoxm.MazeGen.MazeGen;
import com.toxicstoxm.Ui.Window;


public class Main {

    public static final int width = 1920;
    public static final int height = 1080;

    public static final int scale = 15;

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