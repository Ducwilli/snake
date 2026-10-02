package com.williamdesmuliers.snake.model;

public enum Direction {

    UP(new Position(0, -1)),
    DOWN(new Position(0, 1)),
    LEFT(new Position(-1, 0)),
    RIGHT(new Position(1, 0));

    private final Position vector;

    Direction(Position vector){
        this.vector = vector;
    }

    public Position getVector(){
        return this.vector;
    }

    public boolean isOpposite(Direction d){
        Position origin = new Position(0, 0);
        Position sommeDir = this.getVector().translate(d.getVector());
        return sommeDir.equals(origin);
    }
}
