package com.toxicstoxm.MazeGen.Boards;

import com.toxicstoxm.Ui.Canvas;
import com.toxicstoxm.Ui.Window;

import java.awt.*;

public class BoolBoard implements Board<Boolean> {

    boolean[][] board;

    public BoolBoard(int width, int height) {
        board = new boolean[height][width];
    }

    @Override
    public Boolean get(int x, int y) {
        return board[y][x];
    }

    public void enable(int x, int y) {
        board[y][x] = true;
    }

    public void disable(int x, int y) {
        board[y][x] = false;
    }

    @Override
    public void set(int x, int y, Boolean value) {
        board[y][x] = value;
    }

    @Override
    public void clear() {
        board = new boolean[board.length][board[0].length];
    }

    @Override
    public void draw() {

    }

    /*@Override
    public void draw(Window window) {
        int mx = 0;
        int my = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x]) {
                    mx = Math.max(mx, x);
                    my = Math.max(my, x);
                    window.drawPoint(x, y, 1, Color.white, false);
                }
            }
        }
        window.repaint(0, 0, mx, my);
    }*/
}
