package com.toxicstoxm.MazeGen;

import java.awt.*;

public class MazeBoard {

    public record Pixel(int x, int y, Color color) {}

    private Pixel[][] maze = new Pixel[8][8];

}
