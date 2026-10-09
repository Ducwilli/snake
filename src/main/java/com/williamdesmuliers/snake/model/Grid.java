package com.williamdesmuliers.snake.model;

public class Grid {
    
    private final int width;
    private final int height;

    public Grid(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Grid dimensions must be strictly positive (width=" + width + ", height=" + height + ")");
        }
        this.width = width;
        this.height = height;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public boolean contains(Position p){
        boolean isOutLeft = p.getX() < 0;
        boolean isOutRight = p.getX() >= width;
        boolean isOutUP = p.getY() < 0;
        boolean isOutDown = p.getY() >= height;

        return !isOutDown && !isOutLeft && !isOutRight && !isOutUP;
    }

}
