package com.williamdesmuliers.snake.model;

import java.util.ArrayList;
import java.util.Random;

public class Apple {
    
    private Position position;
    private final Random random = new Random();

    public Apple(){
        this.position = new Position(0, 0);
    }

    public Position getPosition(){
        return position;
    }

    public void init(Grid grid, Snake snake){
        respawn(grid, snake);
    }

    private ArrayList<Position> freeCase(Grid grid, Snake snake){
        ArrayList<Position> freePositions = new ArrayList<>();

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Position candidate = new Position(x, y);
                if(!snake.contains(candidate)){
                    freePositions.add(candidate);
                }
            }
        }
        return freePositions;
    }

    public void respawn(Grid grid, Snake snake){
        ArrayList<Position> freePositions = freeCase(grid, snake);
        if(!freePositions.isEmpty()){
            position = freePositions.get(random.nextInt(freePositions.size()));
        }
    }
}
