package com.williamdesmuliers.snake;

import com.williamdesmuliers.snake.control.InputController;
import com.williamdesmuliers.snake.model.Game;
import com.williamdesmuliers.snake.view.GameView;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int CASE_SIZE = 30;
    private static final int FRAME_TIME = 150;   // millisecondes entre deux déplacements
    private final Game game = new Game(20, 20);
    private Canvas canvas;

    private Parent createContent() {
        int canvasHeight = CASE_SIZE * game.getGrid().getHeight();
        int canvasWidth = CASE_SIZE * game.getGrid().getWidth();
        canvas = new Canvas(canvasWidth, canvasHeight);

        return new StackPane(canvas);
    }

    private void startGameLoop(GameView view) {
        KeyFrame frame = new KeyFrame(
            Duration.millis(FRAME_TIME),
            event -> {
                game.tick();
                view.render();
            }
        );

        Timeline timeline = new Timeline(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());
        GameView view = new GameView(canvas, game, CASE_SIZE);
        InputController controller = new InputController(game);

        stage.setScene(scene);
        stage.setTitle("Snake");
        stage.show();
        
        controller.attachTo(scene);
        view.render();
        startGameLoop(view); 
    }
    public static void main(String[] args) {
        launch(args);
    }
}