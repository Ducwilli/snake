package com.williamdesmuliers.snake.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    @DisplayName("chaque direction porte le bon vecteur")
    void getVectorRenvoieLeBonVecteur() {
        assertEquals(new Position(0, -1), Direction.UP.getVector());
        assertEquals(new Position(0, 1), Direction.DOWN.getVector());
        assertEquals(new Position(-1, 0), Direction.LEFT.getVector());
        assertEquals(new Position(1, 0), Direction.RIGHT.getVector());
    }

    @Test
    @DisplayName("les directions verticalement opposées le sont dans les deux sens")
    void isOppositeVraiVertical() {
        assertTrue(Direction.UP.isOpposite(Direction.DOWN));
        assertTrue(Direction.DOWN.isOpposite(Direction.UP));
    }

    @Test
    @DisplayName("les directions horizontalement opposées le sont dans les deux sens")
    void isOppositeVraiHorizontal() {
        assertTrue(Direction.LEFT.isOpposite(Direction.RIGHT));
        assertTrue(Direction.RIGHT.isOpposite(Direction.LEFT));
    }

    @Test
    @DisplayName("deux directions perpendiculaires ne sont pas opposées")
    void isOppositeFauxPerpendiculaire() {
        assertFalse(Direction.UP.isOpposite(Direction.LEFT));
        assertFalse(Direction.UP.isOpposite(Direction.RIGHT));
    }

    @Test
    @DisplayName("une direction n'est pas sa propre opposée")
    void isOppositeFauxMemeDirection() {
        assertFalse(Direction.UP.isOpposite(Direction.UP));
    }
}
