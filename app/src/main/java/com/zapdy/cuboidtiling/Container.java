package com.zapdy.cuboidtiling;

import java.util.ArrayList;

public class Container {
    private final int width;
    private final int height;
    private final int depth;
    private int[][][] map;
    private ArrayList<Box> boxes = new ArrayList<>();
    public boolean isFilled = false;

    public int firstEmptyX = 0;
    public int firstEmptyY = 0;
    public int firstEmptyZ = 0;

    // copy contructor
    private Container(int width, int height, int depth, int[][][] map, ArrayList<Box> boxes, int firstEmptyX, int firstEmptyY, int firstEmptyZ) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.map = map;
        this.boxes = boxes;
        this.firstEmptyX = firstEmptyX;
        this.firstEmptyY = firstEmptyY;
        this.firstEmptyZ = firstEmptyZ;
    }

    public Container(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.map = new int[width][height][depth]; // auto filled with 0s
    }

    public boolean tryAddBox(Box box) {
        if (box.x + box.width > this.width) {
            return false;
        }
        if (box.y + box.height > this.height) {
            return false;
        }
        if (box.z + box.depth > this.depth) {
            return false;
        }

        for (int x = box.x; x < box.x + box.width; x++) {
            for (int y = box.y; y < box.y + box.height; y++) {
                for (int z = box.z; z < box.z + box.depth; z++) {
                    if (map[x][y][z] == 0) {
                        map[x][y][z] = 1;
                    }
                    else {
                        return false; // collision
                    }
                }
            }
        }

        boxes.add(box);
        this.updateIsFilled();
        this.findFirstEmptyPosition();
        return true;
    }

    public void printMap() {
        for (int k = 0; k < this.depth; k++) {
            for (int i = 0; i < this.width; i++) {
                for (int j = 0; j < this.height; j++) {
                    IO.print(this.map[i][j][k] + " ");
                }
                IO.println("");
            }
            IO.println("");
        }
    }

    public Container copy() {
        ArrayList<Box> boxesCopy = new ArrayList<>();
        for (Box box : this.boxes) {
            boxesCopy.add(box); // i dont need a copy of the box because box is immutable
        }

        int[][][] mapCopy = new int[this.width][this.height][this.depth];
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                for (int z = 0; z < this.depth; z++) {
                    mapCopy[x][y][z] = this.map[x][y][z];
                }
            }
        }

        Container containerCopy = new Container(this.width, this.height, this.depth, mapCopy, boxesCopy, this.firstEmptyX, this.firstEmptyY, this.firstEmptyZ);
        return containerCopy;
    }
    
    public void findFirstEmptyPosition() {
        for (int z = 0; z < this.depth; z++) {
            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    if (this.map[x][y][z] == 0) {
                        this.firstEmptyX = x;
                        this.firstEmptyY = y;
                        this.firstEmptyZ = z;
                        return;
                    } 
                }
            }
        }

    }

    private void updateIsFilled() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                for (int z = 0; z < this.depth; z++) {
                    if (this.map[x][y][z] == 0) {
                        this.isFilled = false;
                        return;
                    }
                }
            }
        }
        this.isFilled = true;
    }
}
