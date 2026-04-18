package com.zapdy.cuboidtiling;

public class CuboidTilingSolver {
    public static void solve(int width, int height, int depth) {
        IO.println("Solving " + height + "x" + width + "x" + depth);
        Container mainContainer = new Container(width, height, depth);
        for (int boxWidth = 1; boxWidth <= width; boxWidth++) {
            for (int boxHeight = 1; boxHeight <= height; boxHeight++) {
                for (int boxDepth = 1; boxDepth <= depth; boxDepth++) {
                    IO.print("Box: " + boxWidth + "x" + boxHeight + "x" + boxDepth + "\t");
                    Container containerCopy = mainContainer.copy();
                    Box box = new Box(boxWidth, boxHeight, boxDepth, containerCopy.firstEmptyX, containerCopy.firstEmptyY, containerCopy.firstEmptyZ);

                    if (containerCopy.tryAddBox(box)) {
                        IO.println("Added"); 
                    }
                    else {
                        IO.println("NOT Added"); 
                        continue;
                    }

                    // if filled then increment counter

                    containerCopy.printMap();
                    IO.println("\n");
                }
            }
        }
    }
}
