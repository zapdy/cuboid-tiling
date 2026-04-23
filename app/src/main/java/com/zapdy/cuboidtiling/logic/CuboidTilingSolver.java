package com.zapdy.cuboidtiling.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CuboidTilingSolver {
    private Map<String, ArrayList<Cuboid>> partitions = new HashMap<>();
    public int counter = 0;

    public void solve(int width, int height, int depth) {
        this.partitions.clear();
        counter = 0;
        // IO.println("Solving " + height + "x" + width + "x" + depth);
        Container mainContainer = new Container(width, height, depth);
        solve(mainContainer);
    }

    private void solve(Container container) {
        for (int cuboidWidth = 1; cuboidWidth <= container.width; cuboidWidth++) {
            for (int cuboidHeight = 1; cuboidHeight <= container.height; cuboidHeight++) {
                for (int cuboidDepth = 1; cuboidDepth <= container.depth; cuboidDepth++) {
                    Cuboid cuboid = new Cuboid(cuboidWidth, cuboidHeight, cuboidDepth, container.firstEmptyX, container.firstEmptyY, container.firstEmptyZ);

                    if (!container.canFit(cuboid)) {
                        continue;
                    }

                    container.addCuboid(cuboid);
                    if (container.isFilled) {
                        counter++;  
                        String partition = Arrays.asList(container.getPartition()).toString();
                        if (!this.partitions.containsKey(partition)) {
                            ArrayList<Cuboid> cuboids = new ArrayList<>(container.getCuboids());
                            this.partitions.put(partition, cuboids);
                        }
                    } 
                    else {
                        solve(container);
                    }
                    container.removeCuboid(cuboid);
                }
            }
        }
    }
    public Map<String, ArrayList<Cuboid>> getPartitions() {
        return this.partitions;
    }
}
