package com.williamdesmuliers.snake.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class Snake {
    
    private Deque<Position> snake;

     public void init(Grid g){
        Position origineSnake = new Position(g.getWidth() / 2 ,g.getHeight() / 3);
        snake.addFirst(origineSnake);
        this.move(Direction.RIGHT, true);
        this.move(Direction.RIGHT, true);
    }

    public Snake(){
        this.snake = new ArrayDeque<Position>();
    }

    public void move(Direction d) {
        move(d, false);
    }

    public void move(Direction d, boolean grow){
        Position v = d.getVector();
        Position lastHead = snake.getFirst();
        snake.addFirst(lastHead.translate(v));
        if(!grow){
            snake.removeLast();
        }
    }

    public Position getHead(){
        return snake.getFirst();
    }

    public boolean contains(Position p){
        return snake.contains(p);
    }

    public Deque<Position> getSnake(){
        return snake;
    }

}
