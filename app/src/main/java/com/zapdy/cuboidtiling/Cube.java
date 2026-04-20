package com.zapdy.cuboidtiling;

public record Cube(int width, int height, int depth, int x, int y, int z){
    @Override
    public String toString() {
        int min, mid, max;
        if (width <= height) {
            if (height <= depth) {
                min = width;  
                mid = height; 
                max = depth;
            } 
            else if (width <= depth) {
                min = width;  
                mid = depth;  
                max = height;
            } 
            else {
                min = depth;  
                mid = width;  
                max = height;
            }
        } 
        else {
            if (width <= depth) {
                min = height; 
                mid = width;  
                max = depth;
            } 
            else if (height <= depth) {
                min = height; 
                mid = depth;  
                max = width;
            } 
            else {
                min = depth;  
                mid = height; 
                max = width;
            }
        }
        return min + "x" + mid + "x" + max;
    }
}
