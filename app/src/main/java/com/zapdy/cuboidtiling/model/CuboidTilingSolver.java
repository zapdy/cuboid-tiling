package com.zapdy.cuboidtiling.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CuboidTilingSolver {
    private Map<String, ArrayList<Cube>> partitions = new HashMap<>();
    public int counter = 0;

    public void solve(int width, int height, int depth) {
        this.partitions.clear();
        counter = 0;
        // IO.println("Solving " + height + "x" + width + "x" + depth);
        Container mainContainer = new Container(width, height, depth);
        solve(mainContainer);
    }

    private void solve(Container container) {
        for (int cubeWidth = 1; cubeWidth <= container.width; cubeWidth++) {
            for (int cubeHeight = 1; cubeHeight <= container.height; cubeHeight++) {
                for (int cubeDepth = 1; cubeDepth <= container.depth; cubeDepth++) {
                    // IO.print("Cube: " + cubeWidth + "x" + cubeHeight + "x" + cubeDepth + "\n");

                    Cube cube = new Cube(cubeWidth, cubeHeight, cubeDepth, container.firstEmptyX, container.firstEmptyY, container.firstEmptyZ);
                    
                    if (!container.canFit(cube)) {
                        continue;
                    }
                    
                    container.addCube(cube);
                    if (container.isFilled) {
                        counter++;  
                        String partition = Arrays.asList(container.getPartition()).toString();
                        if (!this.partitions.containsKey(partition)) {
                            ArrayList<Cube> cubes = new ArrayList<>(container.getCubes());
                            this.partitions.put(partition, cubes);
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
        return this.partitions;
    }
}
