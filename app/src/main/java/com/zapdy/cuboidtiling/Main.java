package com.zapdy.cuboidtiling;

public class Main {
    public static void main(String[] args) {
        CuboidTilingSolver solver = new CuboidTilingSolver(3, 3, 2);
        solver.solve();
        IO.println("all possible sequences of cube placements: " + solver.counter);
        IO.println("partitions of possible sequences of cube placements: " + solver.partitions.size());
    }
}
