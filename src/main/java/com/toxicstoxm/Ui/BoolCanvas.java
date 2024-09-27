package com.toxicstoxm.Ui;

import com.toxicstoxm.Main;

import javax.swing.*;
import java.awt.*;

public class BoolCanvas extends JPanel {

    boolean[][] board;

    boolean[][] currentState;

    public BoolCanvas(int width, int height) {
        setOpaque(true);
        board = new boolean[height][width];
        currentState = new boolean[height][width];
    }

    public Boolean get(int x, int y) {
        return board[y][x];
    }

    public void enable(int x, int y) {
        board[y][x] = true;
    }

    public void disable(int x, int y) {
        board[y][x] = false;
    }


    public void set(int x, int y, Boolean value) {
        board[y][x] = value;
    }

    @Override
    protected void paintComponent(Graphics g) {
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
