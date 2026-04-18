package com.zapdy.cuboidtiling;

public class CuboidTilingSolver {
    public static void solve(int width, int height, int depth) {
        Container container = new Container(width, height, depth);
        IO.println("Solving " + height + "x" + width + "x" + depth);
    }
}
