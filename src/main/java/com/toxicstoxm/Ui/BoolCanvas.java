package com.toxicstoxm.Ui;

import com.toxicstoxm.Main;

import javax.swing.*;
import java.awt.*;

public class BoolCanvas extends JPanel {

    public boolean[][] board;

    public boolean[][] currentState;

    public BoolCanvas(int width, int height) {
        setOpaque(true);
        board = new boolean[height][width];
        currentState = new boolean[height][width];
        setIgnoreRepaint(true);
    }

    public Boolean get(Point point) {
        return board[point.y][point.x];
    }

    public void enable(Point point) {
        board[point.y][point.x] = true;
    }

    public void disable(Point point) {
        board[point.y][point.x] = false;
    }

    public void set(Point point, Boolean value) {
        board[point.y][point.x] = value;
    }

    public boolean repaint = true;


    @Override
    protected void paintComponent(Graphics g) {
        if (!repaint) return;
        Graphics2D g2d = (Graphics2D) g.create();

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                boolean newState = board[y][x];
                if (newState != currentState[y][x]) {
                    if (newState) {
                        g2d.setColor(Color.WHITE);
                    } else g2d.setColor(Color.BLACK);
                    g2d.fillRect(x, y, Main.scale, Main.scale);
                    currentState[y][x] = newState;
                }
            }
        }
        g2d.dispose();
        super.paintComponent(g2d);
    }
}
