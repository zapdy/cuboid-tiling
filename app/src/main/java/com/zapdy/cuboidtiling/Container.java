package com.zapdy.cuboidtiling;

import java.util.ArrayList;
import java.util.Arrays;

public class Container {
    private final int width;
    private final int height;
    private final int depth;
    private short[][][] map;
    private ArrayList<Cube> cubes = new ArrayList<>();
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

    public void addCube(Cube cube) {
        for (int x = cube.x(); x < cube.x() + cube.width(); x++) {
            for (int y = cube.y(); y < cube.y() + cube.height(); y++) {
                for (int z = cube.z(); z < cube.z() + cube.depth(); z++) {
                    if (map[x][y][z] == 0) {
                        map[x][y][z] = 1;
                    }
                    else {
                        throw new RuntimeException("The cube doesn't fit in container");
                    }
                }
            }
        }

        cubes.add(cube);
        occupiedCells += cube.width() * cube.height() * cube.depth();
        isFilled = occupiedCells == this.width * this.height * this.depth;
        this.findFirstEmptyPosition();
    }

    public void removeCube(Cube cube) {
        for (int x = cube.x(); x < cube.x() + cube.width(); x++) {
            for (int y = cube.y(); y < cube.y() + cube.height(); y++) {
                for (int z = cube.z(); z < cube.z() + cube.depth(); z++) {
                    map[x][y][z] = 0;
                }
            }
        }

        cubes.remove(cube);
        occupiedCells -= cube.width() * cube.height() * cube.depth();
        isFilled = false;
        this.findFirstEmptyPosition();
    }

    public boolean canFit(Cube cube) {
        if (cube.x() + cube.width() > this.width) {
            return false;
        }
        if (cube.y() + cube.height() > this.height) {
            return false;
        }
        if (cube.z() + cube.depth() > this.depth) {
            return false;
        }

        for (int x = cube.x(); x < cube.x() + cube.width(); x++) {
            for (int y = cube.y(); y < cube.y() + cube.height(); y++) {
                for (int z = cube.z(); z < cube.z() + cube.depth(); z++) {
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
        String[] partition = new String[this.cubes.size()];
           for (int i = 0; i < this.cubes.size(); i++) {
               partition[i] = this.cubes.get(i).toString();
        }
        Arrays.sort(partition);
        return partition;
    }

    public ArrayList<Cube> getCubes() {
        return cubes;
    }
}
