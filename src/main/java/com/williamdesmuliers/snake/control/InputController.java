package com.williamdesmuliers.snake.control;

import com.williamdesmuliers.snake.model.Game;
import com.williamdesmuliers.snake.model.GameState;
import com.williamdesmuliers.snake.model.Direction;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputController {

    private final Game game;

    public InputController(Game game) {
        this.game = game;
    }

    public void attachTo(Scene scene) {
        scene.setOnKeyPressed(event -> handleKey(event));
    }

    private void handleKey(KeyEvent event) {
        if (game.getState() == GameState.RUNNING) {
            switch (event.getCode()) {
                case UP, Z    -> game.setDirection(Direction.UP);
                case DOWN, S  -> game.setDirection(Direction.DOWN);
                case LEFT, Q  -> game.setDirection(Direction.LEFT);
                case RIGHT, D -> game.setDirection(Direction.RIGHT);
                default -> { }
            }
        } else {
            if (event.getCode() == KeyCode.ENTER) {
                game.init();
            }
        }
    }
    
}
