package com.zapdy.cuboidtiling;

public class Main {
    public static void main(String[] args) {
        CuboidTilingSolver solver = new CuboidTilingSolver(2, 2, 2);
        solver.solve();
        IO.println("counter: " + solver.counter);
    }
}
