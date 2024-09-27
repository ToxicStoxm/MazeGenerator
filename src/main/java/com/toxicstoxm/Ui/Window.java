package com.toxicstoxm.Ui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Canvas canvas;

    public Window(int width, int height) {

        setSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.BLACK);

        canvas = new Canvas();
        canvas.setPreferredSize(getSize());
        this.add(canvas);
    }

    /*public void draw(int x, int y, int width, int height) {
        canvas.addShape(new Rectangle(x, y, width, height));
        canvas.repaint();
    }

    public void draw(int x, int y, int width, int height, Color color) {
        draw(x, y, width, height, color, true);
    }

    public void draw(int x, int y, int width, int height, Color color, boolean repaint) {
        canvas.addShape(new Rectangle(x, y, width, height), color);
        if (repaint) canvas.repaint();
    }

    public void drawPixel(int x, int y) {
        draw(x, y, 1, 1);
    }

    public void drawPoint(int x, int y, int thickness) {
        draw(x, y, thickness, thickness);
    }
    public void drawPoint(int x, int y, int thickness, Color color) {
        draw(x, y, thickness, thickness, color);
    }

    public void drawPoint(int x, int y, int thickness, Color color, boolean repaint) {
        draw(x, y, thickness, thickness, color, repaint);
    }*/

}
