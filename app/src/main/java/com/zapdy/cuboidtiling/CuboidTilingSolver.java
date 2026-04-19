package com.zapdy.cuboidtiling;

public class CuboidTilingSolver {
    private int width;
    private int height;
    private int depth;
    
    public int counter = 0;

    CuboidTilingSolver(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    
    public void solve() {
        Container mainContainer = new Container(width, height, depth);
        solve(mainContainer);
    }

    private void solve(Container container) {
        for (int boxWidth = 1; boxWidth <= width; boxWidth++) {
            for (int boxHeight = 1; boxHeight <= height; boxHeight++) {
                for (int boxDepth = 1; boxDepth <= depth; boxDepth++) {
                    IO.print("Box: " + boxWidth + "x" + boxHeight + "x" + boxDepth + "\n");

                    Container containerCopy = container.copy();
                    Box box = new Box(boxWidth, boxHeight, boxDepth, containerCopy.firstEmptyX, containerCopy.firstEmptyY, containerCopy.firstEmptyZ);
                    
                    boolean isAdded = containerCopy.tryAddBox(box);
                    
                    // containerCopy.printMap();
                    // IO.println("\n");

                    if (!isAdded) {
                        // IO.println("couldnt add box");
                        continue;
                    }
                    
                    if (containerCopy.isFilled) {
                        // IO.println("container is successfully filled!");
                        counter++;  
                        continue;
                    }
                    
                    // IO.println("RECURSION!");
                    solve(containerCopy);
                }
            }
        }
    }
}
