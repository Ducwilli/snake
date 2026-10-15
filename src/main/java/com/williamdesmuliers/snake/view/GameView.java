package com.williamdesmuliers.snake.view;

import com.williamdesmuliers.snake.model.Game;
import com.williamdesmuliers.snake.model.Position;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameView {

    private final GraphicsContext gc;
    private final Game game;
    private final int caseSize; 

    public GameView(Canvas canvas, Game game, int caseSize) {
        gc = canvas.getGraphicsContext2D();
        this.game = game;
        this.caseSize = caseSize;
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

    public void render() {
        int canvasHeight = caseSize * game.getGrid().getHeight();
        int canvasWidth = caseSize * game.getGrid().getWidth();
        Position pomme = game.getApple().getPosition();

        // 1. Effacer le fond (repartir d'une ardoise propre)
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);

        // 2. Dessiner la pomme
        gc.setFill(Color.RED);
        gc.fillRect(pomme.getX() * caseSize, pomme.getY() * caseSize, caseSize, caseSize);

        // 3. Dessiner chaque segment du serpent
        gc.setFill(Color.GREEN);
        for (Position p : game.getSnake().getSnake()) {
            gc.fillRect(p.getX() * caseSize, p.getY() * caseSize, caseSize, caseSize);
        }

        drawGrid();
    }

}
