package com.williamdesmuliers.snake.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GridTest {

    @Test
    @DisplayName("une position au centre est dans la grille")
    void containsVraiAuCentre() {
        Grid grid = new Grid(20, 20);
        assertTrue(grid.contains(new Position(10, 10)));
    }

    @Test
    @DisplayName("les coins valides extrêmes sont dans la grille")
    void containsVraiAuxCoins() {
        Grid grid = new Grid(20, 20);
        assertTrue(grid.contains(new Position(0, 0)));
        assertTrue(grid.contains(new Position(19, 19)));
    }

    @Test
    @DisplayName("une position un cran hors des bords n'est pas dans la grille")
    void containsFauxJusteDehors() {
        Grid grid = new Grid(20, 20);
        assertFalse(grid.contains(new Position(-1, 0)));
        assertFalse(grid.contains(new Position(20, 0)));
        assertFalse(grid.contains(new Position(0, -1)));
        assertFalse(grid.contains(new Position(0, 20)));
    }

    @Test
    @DisplayName("une dimension nulle ou négative est refusée à la construction")
    void constructeurRefuseDimensionsInvalides() {
        assertThrows(IllegalArgumentException.class, () -> new Grid(0, 10));
        assertThrows(IllegalArgumentException.class, () -> new Grid(10, 0));
        assertThrows(IllegalArgumentException.class, () -> new Grid(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> new Grid(10, -1));
    }

    @Test
    @DisplayName("des dimensions valides ne lèvent pas d'exception")
    void constructeurAccepteDimensionsValides() {
        assertDoesNotThrow(() -> new Grid(10, 10));
    }
}
