package com.toxicstoxm.MazeGen.Boards;

public interface Board<T> {
    T get(int x, int y);
    void set(int x, int y, T value);
    void clear();
    void draw();
}
