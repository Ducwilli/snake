package com.williamdesmuliers.snake.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class HighScoreRepositoryTest {

    @Test
    @DisplayName("load renvoie 0 quand aucun fichier n'existe")
    void loadSansFichierRenvoieZero(@TempDir Path dossier) {
        Path fichier = dossier.resolve("highscore");
        HighScoreRepository repo = new HighScoreRepository(fichier);
        assertEquals(0, repo.load());
    }

    @Test
    @DisplayName("un score sauvegardé peut être relu")
    void saveePuisLoadRetrouveLeScore(@TempDir Path dossier) {
        Path fichier = dossier.resolve("highscore");
        HighScoreRepository repo = new HighScoreRepository(fichier);

        repo.save(42);
        assertEquals(42, repo.load());
    }

    @Test
    @DisplayName("save écrase l'ancien score")
    void saveEcraseAncienScore(@TempDir Path dossier) {
        Path fichier = dossier.resolve("highscore");
        HighScoreRepository repo = new HighScoreRepository(fichier);

        repo.save(10);
        repo.save(25);
        assertEquals(25, repo.load());
    }

    @Test
    @DisplayName("un fichier au contenu invalide fait renvoyer 0")
    void contenuInvalideRenvoieZero(@TempDir Path dossier) throws Exception {
        Path fichier = dossier.resolve("highscore");
        java.nio.file.Files.writeString(fichier, "pas un nombre");
        HighScoreRepository repo = new HighScoreRepository(fichier);

        assertEquals(0, repo.load());
    }
}
