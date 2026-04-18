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

    public void addBox(Box box) {
        boxes.add(box);
        // adding box to map
    }
}
