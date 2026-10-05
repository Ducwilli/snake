package com.williamdesmuliers.snake.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SnakeTest {

    private Snake snake;
    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid(20, 20);
        snake = new Snake();
        snake.init(grid);
    }

    @Test
    @DisplayName("après init, le serpent occupe 3 cases")
    void initDonneTroisCases() {
        assertEquals(3, snake.getSnake().size());
    }

    @Test
    @DisplayName("après init, la tête est à la position de départ attendue")
    void initPlaceLaTeteAuBonEndroit() {
        // grille 20x20 : width/2 = 10 + 2, height/3 = 6
        assertEquals(new Position(12, 6), snake.getHead());
    }

    @Test
    @DisplayName("un move simple garde la taille constante")
    void moveGardeLaTailleConstante() {
        snake.move(Direction.RIGHT);
        assertEquals(3, snake.getSnake().size());
    }

    @Test
    @DisplayName("un move simple fait avancer la tête d'une case")
    void moveFaitAvancerLaTete() {
        Position avant = snake.getHead();
        snake.move(Direction.RIGHT);
        Position attendue = avant.translate(Direction.RIGHT.getVector());
        assertEquals(attendue, snake.getHead());
    }

    @Test
    @DisplayName("un move avec grow augmente la taille de 1")
    void growAugmenteLaTaille() {
        snake.move(Direction.RIGHT, true);
        assertEquals(4, snake.getSnake().size());
    }

    @Test
    @DisplayName("après grow, l'ancienne queue est toujours présente")
    void growConserveLaQueue() {
        Position ancienneQueue = snake.getSnake().getLast();
        snake.move(Direction.RIGHT, true);
        assertEquals(ancienneQueue, snake.getSnake().getLast());
    }

    @Test
    @DisplayName("contains est vrai pour une case du corps")
    void containsVraiSurLeCorps() {
        Position tete = snake.getHead();
        assertTrue(snake.contains(tete));
    }

    @Test
    @DisplayName("contains est faux pour une case vide")
    void containsFauxSurCaseVide() {
        assertFalse(snake.contains(new Position(0, 0)));
    }

    @Test
    @DisplayName("getHead renvoie la dernière position ajoutée en tête")
    void getHeadRenvoieLaDerniereTete() {
        snake.move(Direction.RIGHT);
        snake.move(Direction.UP);
        Position attendue = new Position(12, 6)
                .translate(Direction.RIGHT.getVector())
                .translate(Direction.UP.getVector());
        assertEquals(attendue, snake.getHead());
    }
}
