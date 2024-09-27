package com.toxicstoxm.Ui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Canvas canvas;

    public Window() {

        setSize(new Dimension(1920, 1080));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setPreferredSize(getSize());
        this.add(canvas);
    }

    public void draw(int x, int y, int width, int height) {
        canvas.addShape(new Rectangle(x, y, width, height));
        canvas.repaint();
    }

    public void draw(int x, int y, int width, int height, Color color) {
        canvas.addShape(new Rectangle(x, y, width, height), color);
        canvas.repaint();
    }

    public void drawPixel(int x, int y) {
        draw(x, y, 1, 1);
    }

    public void drawPoint(int x, int y, int thickness) {
        draw(x, y, thickness, thickness);
    }

}
