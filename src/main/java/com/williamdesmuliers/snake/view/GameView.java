package com.williamdesmuliers.snake.view;

import com.williamdesmuliers.snake.model.Game;
import com.williamdesmuliers.snake.model.GameState;
import com.williamdesmuliers.snake.model.Position;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GameView {

    private final GraphicsContext gc;
    private final Game game;
    private final int caseSize; 

    private final Image appleImage;
    private final Image bodyImage;

    public GameView(Canvas canvas, Game game, int caseSize) {
        gc = canvas.getGraphicsContext2D();
        this.game = game;
        this.caseSize = caseSize;

        appleImage = new Image(getClass().getResourceAsStream("/assets/apple.png"));
        bodyImage = new Image(getClass().getResourceAsStream("/assets/body.png"));  
    }

    private void drawGrid(){
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1);

        for (int i = 0 ; i <= game.getGrid().getWidth() ; i ++){
            int x = i * caseSize;
            gc.strokeLine(x, 0, x, game.getGrid().getHeight() * caseSize);
        }
        for (int i = 0 ; i <= game.getGrid().getHeight() ; i ++){
            int y = i * caseSize;
            gc.strokeLine(0, y, game.getGrid().getWidth() * caseSize, y);
        }
    }

    private void drawScore(int highScore){
        String text = "SCORE : " + game.getScore();
        String textBest = "MEILLEUR SCORE : " + Math.max(game.getScore(), highScore);

        gc.setFill(Color.WHITE);
        gc.fillText(text, 15, 20);
        gc.fillText(textBest, 15, 40);
    }

    private void drawGameOver(){
        double x = game.getGrid().getWidth() * caseSize / 2.0;
        double y = game.getGrid().getHeight() * caseSize / 2.0;

        gc.setFill(Color.WHITE);

        switch (game.getState()) {
            case GameState.WON -> gc.fillText("Victoire", x, y);
            case GameState.LOST -> gc.fillText("Perdu", x, y);
            default -> { }
        }
    }

    public void render(int highScore) {
        int canvasHeight = caseSize * game.getGrid().getHeight();
        int canvasWidth = caseSize * game.getGrid().getWidth();
        Position apple = game.getApple().getPosition();

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);

        gc.drawImage(appleImage, apple.getX() * caseSize, apple.getY() * caseSize, caseSize, caseSize);

        for (Position p : game.getSnake().getSnake()) {
            gc.drawImage(bodyImage, p.getX() * caseSize, p.getY() * caseSize, caseSize, caseSize);
        }

        drawGrid();
        drawScore(highScore);
        if(game.getState() != GameState.RUNNING){drawGameOver();}
    }

}
