package com.williamdesmuliers.snake.control;

import com.williamdesmuliers.snake.model.Game;
import com.williamdesmuliers.snake.view.GameView;
import com.williamdesmuliers.snake.model.Direction;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class InputController {

    private final Game game;
    private final GameView view;

    public InputController(Game game, GameView view) {
        this.game = game;
        this.view = view;
    }

    public void attachTo(Scene scene) {
        scene.setOnKeyPressed(event -> handleKey(event));
    }

    private void handleKey(KeyEvent event) {
        switch (event.getCode()) {
            case UP, Z    -> game.setDirection(Direction.UP);
            case DOWN, S  -> game.setDirection(Direction.DOWN);
            case LEFT, Q  -> game.setDirection(Direction.LEFT);
            case RIGHT, D -> game.setDirection(Direction.RIGHT);
            default -> { }   // toute autre touche : on ignore
        }
        game.tick();
        view.render();
    }
    
}
