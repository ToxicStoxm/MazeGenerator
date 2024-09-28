package com.toxicstoxm.MazeGen;

import com.toxicstoxm.Main;
import com.toxicstoxm.MazeSolve.Dijkstra;
import com.toxicstoxm.Ui.BoolCanvas;
import com.toxicstoxm.Ui.Window;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeGen {

    public static final long FPS = 1000000000;
    public static final long NSPF = 1000000000 / FPS;
    public static long startOfFrame = System.currentTimeMillis();

    private static BoolCanvas canvas;
    private final Window window;
    private final List<Direction> directions;


    public MazeGen(int width, int height, Window window) {
        canvas = new BoolCanvas(width, height);
        window.add(canvas);
        this.window = window;

        directions = new ArrayList<>();

        final int plus = Main.scale;
        final int minus = -1 * Main.scale;

        directions.add(new Direction() {

            @Override
            public Point[] requiredSpace(Point p) {
                Point[] points = new Point[5];
                Point tst1 = adjust(p, plus);
                Point tst2 = adjust(p, 2 * plus);

                points[0] = adjustX(tst1, plus);
                points[1] = adjustX(tst1, minus);
                points[2] = adjustX(tst2, plus);
                points[3] = adjustX(tst2, minus);
                points[4] = adjustY(tst2, plus);

                return points;
            }

            @Override
            public Point adjust(Point p, int amount) {
                return new Point(p.x, p.y + amount);
            }

            @Override
            public String getName() {
                return "UP";
            }

            @Override
            public String toString() {
                return getName();
            }
        });

        directions.add(new Direction() {

            @Override
            public Point[] requiredSpace(Point p) {
                Point[] points = new Point[5];
                Point tst1 = adjust(p, plus);
                Point tst2 = adjust(p, 2 * plus);

                points[0] = adjustY(tst1, plus);
                points[1] = adjustY(tst1, minus);
                points[2] = adjustY(tst2, plus);
                points[3] = adjustY(tst2, minus);
                points[4] = adjustX(tst2, plus);

                return points;
            }

            @Override
            public Point adjust(Point p, int amount) {
                return new Point(p.x + amount, p.y);
            }

            @Override
            public String getName() {
                return "RIGHT";
            }

            @Override
            public String toString() {
                return getName();
            }
        });

        directions.add(new Direction() {

            @Override
            public Point[] requiredSpace(Point p) {
                Point[] points = new Point[5];
                Point tst1 = adjust(p, plus);
                Point tst2 = adjust(p, 2 * plus);

                points[0] = adjustX(tst1, plus);
                points[1] = adjustX(tst1, minus);
                points[2] = adjustX(tst2, plus);
                points[3] = adjustX(tst2, minus);
                points[4] = adjustY(tst2, minus);

                return points;
            }

            @Override
            public Point adjust(Point p, int amount) {
                return new Point(p.x, p.y - amount);
            }

            @Override
            public String getName() {
                return "DOWN";
            }

            @Override
            public String toString() {
                return getName();
            }
        });

        directions.add(new Direction() {
            @Override
            public Point[] requiredSpace(Point p) {
                Point[] points = new Point[5];
                Point tst1 = adjust(p, plus);
                Point tst2 = adjust(p, 2 * plus);

                points[0] = adjustY(tst1, plus);
                points[1] = adjustY(tst1, minus);
                points[2] = adjustY(tst2, plus);
                points[3] = adjustY(tst2, plus);
                points[4] = adjustX(tst2, minus);

                return points;
            }

            @Override
            public Point adjust(Point p, int amount) {
                return new Point(p.x - amount, p.y);
            }

            @Override
            public String getName() {
                return "LEFT";
            }

            @Override
            public String toString() {
                return getName();
            }
        });
    }

    private int pathX = 0;
    private int pathY = 0;

    private Point adjustX(Point p, int x) {
        return adjustpoint(p, x, 0);
    }

    private Point adjustY(Point p, int y) {
        return adjustpoint(p, 0, y);
    }

    private Point adjustpoint(Point p, int x, int y) {
        return new Point(p.x + x, p.y + y);
    }

    private long frameCounter = 0;

    public void generateMaze() {

        canvas.repaint = false;
        canvas.enable(new Point(0, 0));
        //canvas.repaint();

        for (int iterY = 0; iterY < Main.scaled_height; iterY++) {
            for (int iterX = 0; iterX < Main.scaled_width; iterX++) {
                genPath(iterX, iterY, 0);
                //canvas.repaint();
                canvas.repaint = true;
                canvas.repaint();
                canvas.repaint = false;
            }
        }
        canvas.repaint = true;
        canvas.repaint();

        Dijkstra dijkstra = new Dijkstra();

        //dijkstra.dijkstra(boolBoardToIntBoard(canvas.board), 1);

    }

    private int[][] boolBoardToIntBoard(boolean[][] boolBoard) {
        int[][] result = new int[boolBoard.length][boolBoard[0].length];
        for (int y = 0; y < boolBoard.length; y++) {
            for (int x = 0; x < boolBoard[y].length; x++) {
                result[y][x] = boolBoard[y][x] ? 1 : 0;
            }
        }
        return result;
    }

    record Result(boolean success, int x, int y) {}

    interface Direction {
        Point[] requiredSpace(Point p);
        default Point adjust(Point p) {
           return adjust(p, 2);
        }
        Point adjust(Point p, int amount);
        default boolean isAvailable(Point p, Rectangle bounds) {
            Point tst1 = adjust(p, 1);
            Point tst2 = adjust(p);
            if (!bounds.contains(p) || !bounds.contains(tst1) || !bounds.contains(tst2) || canvas.get(tst1) || canvas.get(tst2)) return false;
            for (Point ps : requiredSpace(p)) {
                if (bounds.contains(ps) && canvas.get(ps)) return false;
            }
           return true;
        }
        String getName ();
    }


    private boolean genPath(int iterX, int iterY, int cnt) {
        int pathX = iterX;
        int pathY = iterY;
        Result result;
        if (canvas.get(new Point(iterX, iterY))) {
            do {
                log("----- Frame #" + frameCounter + " -----");
                startOfFrame = System.nanoTime();
                log("Iterator pos: x = " + pathX + " y = " + pathY);
                log("Path pos: x = " + pathX + " y = " + pathY);
                result = genPathSegment(pathX, pathY);
                log("Result: success = " + result.success + " x = " + result.x + " y = " + result.y);
                log("Repainting...");

                pathX = result.x;
                pathY = result.y;

                try {
                    frameCounter++;
                    long timeElapsed = System.nanoTime() - startOfFrame;
                    Thread.sleep(Duration.ofNanos(NSPF - timeElapsed));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } while (result.success);
        } else return false;

        return cnt >= 4 || genPath(iterX, iterY, cnt + 1);
    }


    private Result genPathSegment(int pathX, int pathY) {

        Point point = new Point(pathX, pathY);
        if (canvas.get(point)) {
            Rectangle bounds = new Rectangle(0, 0, Main.scaled_width, Main.scaled_height);

            Collections.shuffle(directions);
            log("Direction list: " + directions);
            for (Direction direction : directions) {

                if (direction.isAvailable(point, bounds)) {
                    log("Available");
                    canvas.enable(point);
                    log("Enabled: x = " + point.x + " y = " + point.y);
                    point = direction.adjust(point, 1);
                    canvas.enable(point);
                    point = direction.adjust(point, 1);
                    canvas.enable(point);
                    log("Enabled: x = " + point.x + " y = " + point.y);
                    return new Result(true, point.x, point.y);
                } else log("Unavailable");
            }
        }
        return new Result(false, pathX, pathY);
    }

    private void log(String message) {
        if (false) System.out.println(message);
    }

}
