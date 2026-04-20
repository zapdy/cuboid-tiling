package com.zapdy.cuboidtiling;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CuboidTilingSolver {
    private int width;
    private int height;
    private int depth;

    public Set<List<String>> partitions = new HashSet<>();
    public int counter = 0;

    CuboidTilingSolver(int width, int height, int depth) {
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
                        List<String> partition = Arrays.asList(container.getPartition());
                        partitions.add(partition);
                    } 
                    else {
                        solve(container);
                    }
                    container.removeCube(cube);
                }
            }
        }
    }
}
