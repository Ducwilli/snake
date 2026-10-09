package com.williamdesmuliers.snake.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class AppleTest {

    private Grid grid;
    private Snake snake;
    private Apple apple;

    @BeforeEach
    void setUp() {
        grid = new Grid(20, 20);
        snake = new Snake();
        snake.init(grid);
        apple = new Apple();
        apple.init(grid, snake);
    }

    @RepeatedTest(100)
    @DisplayName("la pomme n'apparaît jamais sous le serpent")
    void respawnJamaisSousLeSerpent() {
        apple.respawn(grid, snake);
        assertFalse(snake.contains(apple.getPosition()));
    }

    @RepeatedTest(100)
    @DisplayName("la pomme reste toujours dans la grille")
    void respawnToujoursDansLaGrille() {
        apple.respawn(grid, snake);
        assertTrue(grid.contains(apple.getPosition()));
    }

    @Test
    @DisplayName("après init, la pomme est sur une case valide et libre")
    void initPlaceUnePommeValide() {
        assertTrue(grid.contains(apple.getPosition()));
        assertFalse(snake.contains(apple.getPosition()));
    }
}
