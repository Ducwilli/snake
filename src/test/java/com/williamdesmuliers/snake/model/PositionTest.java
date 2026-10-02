package com.williamdesmuliers.snake.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("translate additionne correctement deux positions")
    void translateAdditionnePositif() {
        Position depart = new Position(2, 3);
        Position resultat = depart.translate(new Position(1, 0));
        assertEquals(new Position(3, 3), resultat);
    }

    @Test
    @DisplayName("translate fonctionne avec des valeurs négatives")
    void translateAvecNegatifs() {
        Position depart = new Position(5, 5);
        Position resultat = depart.translate(new Position(-2, -3));
        assertEquals(new Position(3, 2), resultat);
    }

    @Test
    @DisplayName("deux positions aux mêmes coordonnées sont égales")
    void equalsVraiSiMemesCoordonnees() {
        assertEquals(new Position(3, 7), new Position(3, 7));
    }

    @Test
    @DisplayName("deux positions aux coordonnées inversées sont différentes")
    void equalsFauxSiCoordonneesInversees() {
        assertNotEquals(new Position(3, 7), new Position(7, 3));
    }

    @Test
    @DisplayName("deux positions différant par un seul axe sont différentes")
    void equalsFauxSiUnAxeDiffere() {
        assertNotEquals(new Position(3, 7), new Position(3, 8));
    }

    @Test
    @DisplayName("deux positions égales ont le même hashCode")
    void hashCodeCoherentAvecEquals() {
        Position a = new Position(3, 7);
        Position b = new Position(3, 7);
        assertTrue(a.hashCode() == b.hashCode());
    }
}
