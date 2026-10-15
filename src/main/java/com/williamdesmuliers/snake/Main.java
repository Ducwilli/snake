package com.williamdesmuliers.snake;

import com.williamdesmuliers.snake.model.Game;
import com.williamdesmuliers.snake.view.GameView;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int CASE_SIZE = 30;
    private final Game game = new Game(20, 20);
    private Canvas canvas;

    private Parent createContent() {
        int canvasHeight = CASE_SIZE * game.getGrid().getHeight();
        int canvasWidth = CASE_SIZE * game.getGrid().getWidth();
        canvas = new Canvas(canvasWidth, canvasHeight);

        return new StackPane(canvas);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setTitle("Snake");
        stage.show();
        
        GameView view = new GameView(canvas, game, CASE_SIZE);
        view.render();
    }
    public static void main(String[] args) {
        launch(args);
    }
}