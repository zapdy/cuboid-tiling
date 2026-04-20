package com.zapdy.cuboidtiling.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CuboidTilingSolver {
    private int width;
    private int height;
    private int depth;
    private Map<String, ArrayList<Cube>> partitions = new HashMap<>();
    public int counter = 0;

    public CuboidTilingSolver(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    
    public void solve() {
        IO.println("Solving " + height + "x" + width + "x" + depth);
        Container mainContainer = new Container(width, height, depth);
        solve(mainContainer);
    }

    private void solve(Container container) {
        for (int cubeWidth = 1; cubeWidth <= width; cubeWidth++) {
            for (int cubeHeight = 1; cubeHeight <= height; cubeHeight++) {
                for (int cubeDepth = 1; cubeDepth <= depth; cubeDepth++) {
                    // IO.print("Cube: " + cubeWidth + "x" + cubeHeight + "x" + cubeDepth + "\n");

                    Cube cube = new Cube(cubeWidth, cubeHeight, cubeDepth, container.firstEmptyX, container.firstEmptyY, container.firstEmptyZ);
                    
                    if (!container.canFit(cube)) {
                        continue;
                    }
                    
                    container.addCube(cube);
                    if (container.isFilled) {
                        counter++;  
                        String partition = Arrays.asList(container.getPartition()).toString();
                        if (!partitions.containsKey(partition)) {
                            ArrayList<Cube> cubes = new ArrayList<>(container.getCubes());
                            partitions.put(partition, cubes);
                        }
                    } 
                    else {
                        solve(container);
                    }
                    container.removeCube(cube);
                }
            }
        }
    }
    public Map<String, ArrayList<Cube>> getPartitions() {
        return partitions;
    }
}
