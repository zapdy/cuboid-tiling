package com.zapdy.cuboidtiling;

import java.util.ArrayList;

public class Container {
    private final int width;
    private final int height;
    private final int depth;
    private int[][][] map;
    private ArrayList<Box> boxes = new ArrayList<Box>();

    public Container(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.map = new int[width][height][depth]; // auto filled with 0s
    }

    public boolean tryAddBox(Box box) {
        if (box.x + box.width > width) {
            return false;
        }
        if (box.y + box.height > height) {
            return false;
        }
        if (box.z + box.depth > depth) {
            return false;
        }
        for (int i = box.x; i < box.x + box.width; i++) {
            for (int j = box.y; j < box.y + box.height; j++) {
                for (int k = box.z; k < box.z + box.depth; k++) {
                    if (map[i][j][k] == 0) {
                        map[i][j][k] = 1;
                    }
                    else {
                        return false; // collision
                    }
                }
            }
        }
        boxes.add(box);
        return true;
    }
}
