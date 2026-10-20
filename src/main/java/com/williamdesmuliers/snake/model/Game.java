package com.williamdesmuliers.snake.model;

public class Game {

    private final Grid grid;        
    private final Snake snake;      
    private final Apple apple;      
    private Direction direction;   
    private Direction nextDirection;
    private int score;             
    private GameState state; 

    public Game(int width, int height){
        grid = new Grid(width, height);
        snake = new Snake();
        apple = new Apple();
        this.init();
    }

    public void init(){
        snake.init(grid);       
        apple.init(grid, snake); 
        direction = Direction.RIGHT;
        nextDirection = Direction.RIGHT;
        score = 0;
        state = GameState.RUNNING;
    }

    public void setDirection(Direction newDir){
        if(!direction.isOpposite(newDir)){
            nextDirection = newDir;
        }
    }

    private boolean isDeadly(Position futureHead){
        return !grid.contains(futureHead) || snake.contains(futureHead);
    }

    private boolean isApple(Position futureHead){
        return futureHead.equals(apple.getPosition());
    }

    public void tick(){
        if (state != GameState.RUNNING) return;
        
        direction = nextDirection;

        Position futureHead = snake.getHead().translate(direction.getVector());
        if (isDeadly(futureHead)) { 
            state = GameState.LOST; 
            return; 
        }

        boolean willEat = isApple(futureHead);
        snake.move(direction, willEat);
        if (willEat) { 
            score++; 
            
            if(snake.getSnake().size() == grid.getHeight() * grid.getWidth()){
                state = GameState.WON; 
            } else {
                apple.respawn(grid, snake);
            }
        }
    }

    public Grid getGrid(){
        return grid;
    }

    public Snake getSnake(){
        return snake;
    }

    public Apple getApple(){
        return apple;
    }

    public Direction getDirection(){
        return direction;
    }

    public int getScore(){
        return score;
    }

    public GameState getState(){
        return state;
    }
}
