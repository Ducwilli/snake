package com.williamdesmuliers.snake.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(20, 20);
    }

    @Test
    @DisplayName("une nouvelle partie démarre en RUNNING, score 0")
    void nouvellePartieEtatInitial() {
        assertEquals(GameState.RUNNING, game.getState());
        assertEquals(0, game.getScore());
        assertEquals(3, game.getSnake().getSnake().size());
    }

    @Test
    @DisplayName("un tick sans obstacle garde la partie en cours")
    void tickNormalGardeRunning() {
        game.tick();
        assertEquals(GameState.RUNNING, game.getState());
        assertEquals(0, game.getScore());
    }

    @Test
    @DisplayName("foncer dans le mur fait perdre")
    void collisionMurFaitPerdre() {
        // la tête part en (12,6) vers la droite ; le bord droit est à x=19.
        // assez de ticks pour dépasser le mur, quoi qu'il arrive.
        for (int i = 0; i < 30; i++) {
            game.tick();
        }
        assertEquals(GameState.LOST, game.getState());
    }

    @Test
    @DisplayName("un demi-tour direct est ignoré")
    void demiTourIgnore() {
        // le serpent va à droite ; on demande la gauche (demi-tour)
        game.setDirection(Direction.LEFT);
        assertEquals(Direction.RIGHT, game.getDirection());
    }

    @Test
    @DisplayName("un virage à 90° est accepté")
    void virageAccepte() {
        game.setDirection(Direction.UP);
        assertEquals(Direction.UP, game.getDirection());
    }

    @Test
    @DisplayName("manger une pomme augmente le score et la longueur")
    void mangerPommeAugmenteScoreEtLongueur() {
        // on force la pomme devant la tête pour rendre le test déterministe
        Position devantLaTete = game.getSnake().getHead()
                .translate(game.getDirection().getVector());
        placerPommeEn(devantLaTete);

        int longueurAvant = game.getSnake().getSnake().size();
        game.tick();

        assertEquals(1, game.getScore());
        assertEquals(longueurAvant + 1, game.getSnake().getSnake().size());
    }

    @Test
    @DisplayName("se mordre fait perdre")
    void collisionCorpsFaitPerdre() {
        // allonger le serpent à 7 cases en forçant des pommes devant lui
        for (int i = 0; i < 4; i++) {
            Position devant = game.getSnake().getHead()
                    .translate(game.getDirection().getVector());
            placerPommeEn(devant);
            game.tick();
        }
        // le serpent (assez long) s'enroule : la tête revient dans le corps
        game.setDirection(Direction.UP);
        game.tick();
        game.setDirection(Direction.LEFT);
        game.tick();
        game.setDirection(Direction.DOWN);
        game.tick();
        assertEquals(GameState.LOST, game.getState());
    }

    // --- utilitaire : forcer la position de la pomme via réflexion ---
    private void placerPommeEn(Position p) {
        try {
            var champ = Apple.class.getDeclaredField("position");
            champ.setAccessible(true);
            champ.set(game.getApple(), p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
