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
        for (int boxWidth = 1; boxWidth <= width; boxWidth++) {
            for (int boxHeight = 1; boxHeight <= height; boxHeight++) {
                for (int boxDepth = 1; boxDepth <= depth; boxDepth++) {
                    // IO.print("Box: " + boxWidth + "x" + boxHeight + "x" + boxDepth + "\n");

                    Container containerCopy = container.copy();
                    Box box = new Box(boxWidth, boxHeight, boxDepth, containerCopy.firstEmptyX, containerCopy.firstEmptyY, containerCopy.firstEmptyZ);
                    
                    boolean isAdded = containerCopy.tryAddBox(box);
                    if (!isAdded) {
                        continue;
                    }
                    
                    if (containerCopy.isFilled) {
                        counter++;  
                        // containerCopy.printMap();
                        List<String> partition = Arrays.asList(containerCopy.getPartition());
                        partitions.add(partition);
                        continue;
                    }
                    
                    solve(containerCopy);
                }
            }
        }
    }
}
