package com.zapdy.cuboidtiling;

import java.util.Arrays;

public class Box {
    public final int width;
    public final int height;
    public final int depth;
    public final int x;
    public final int y;
    public final int z;

    public Box(int width, int height, int depth, int x, int y, int z) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        int[] sorting = { width, height, depth };
        Arrays.sort(sorting);
        return sorting[0] + "x" + sorting[1] + "x" + sorting[2];
    }
}
