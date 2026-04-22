package com.zapdy.cuboidtiling.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Container {
    public final int width;
    public final int height;
    public final int depth;
    private short[][][] map;
    private ArrayList<Cuboid> cuboids = new ArrayList<>();
    public boolean isFilled = false;
    private int occupiedCells = 0;
    public int firstEmptyX = 0;
    public int firstEmptyY = 0;
    public int firstEmptyZ = 0;

    public Container(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.map = new short[width][height][depth]; // auto filled with 0s
    }

    public void addCuboid(Cuboid cuboid) {
        for (int x = cuboid.x(); x < cuboid.x() + cuboid.width(); x++) {
            for (int y = cuboid.y(); y < cuboid.y() + cuboid.height(); y++) {
                for (int z = cuboid.z(); z < cuboid.z() + cuboid.depth(); z++) {
                    if (map[x][y][z] == 0) {
                        map[x][y][z] = 1;
                    }
                    else {
                        throw new RuntimeException("The cuboid doesn't fit in container");
                    }
                }
            }
        }

        cuboids.add(cuboid);
        occupiedCells += cuboid.width() * cuboid.height() * cuboid.depth();
        isFilled = occupiedCells == this.width * this.height * this.depth;
        this.findFirstEmptyPosition();
    }

    public void removeCuboid(Cuboid cuboid) {
        for (int x = cuboid.x(); x < cuboid.x() + cuboid.width(); x++) {
            for (int y = cuboid.y(); y < cuboid.y() + cuboid.height(); y++) {
                for (int z = cuboid.z(); z < cuboid.z() + cuboid.depth(); z++) {
                    map[x][y][z] = 0;
                }
            }
        }

        cuboids.remove(cuboid);
        occupiedCells -= cuboid.width() * cuboid.height() * cuboid.depth();
        isFilled = false;
        this.findFirstEmptyPosition();
    }

    public boolean canFit(Cuboid cuboid) {
        if (cuboid.x() + cuboid.width() > this.width) {
            return false;
        }
        if (cuboid.y() + cuboid.height() > this.height) {
            return false;
        }
        if (cuboid.z() + cuboid.depth() > this.depth) {
            return false;
        }

        for (int x = cuboid.x(); x < cuboid.x() + cuboid.width(); x++) {
            for (int y = cuboid.y(); y < cuboid.y() + cuboid.height(); y++) {
                for (int z = cuboid.z(); z < cuboid.z() + cuboid.depth(); z++) {
                    if (map[x][y][z] == 1) {
                        return false; // collision
                    }
                }
            }
        }
        return true;
    }
    public void printMap() {
        for (int z = 0; z < this.depth; z++) {
            for (int x = 0; x < this.width; x++) {
                for (int y = 0; y < this.height; y++) {
                    IO.print(this.map[x][y][z] + " ");
                }
                IO.println("");
            }
            IO.println("");
        }
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

    public String[] getPartition() {
        String[] partition = new String[this.cuboids.size()];
           for (int i = 0; i < this.cuboids.size(); i++) {
               partition[i] = this.cuboids.get(i).toString();
        }
        Arrays.sort(partition);
        return partition;
    }

    public ArrayList<Cuboid> getCuboids() {
        return cuboids;
    }
}
