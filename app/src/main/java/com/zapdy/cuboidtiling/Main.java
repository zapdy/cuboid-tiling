package com.zapdy.cuboidtiling;

import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CuboidTilingSolver solver = new CuboidTilingSolver(3, 3, 3);
        solver.solve();
        Map<String, ArrayList<Cube>> partitions = solver.getPartitions();
        IO.println("all possible sequences of cube placements: " + solver.counter);
        IO.println("partitions of possible sequences of cube placements: " + partitions.size());

        /*
        for (Map.Entry<String, ArrayList<Cube>> entry : partitions.entrySet()) {
            String key = entry.getKey();
            ArrayList<Cube> cubes = entry.getValue();
            IO.println(key + cubes.size());
        } 
        */
    }
}
