# Snake

Un clone du jeu **Snake** classique, réalisé en **Java** avec **JavaFX**.

Projet personnel conçu comme un exercice de développement : l'objectif n'était pas
seulement d'avoir un jeu qui marche, mais de produire un code **propre, testé et
réutilisable**, en s'appuyant sur une architecture claire et sur Git/GitHub.

---

## Fonctionnalités

- Déplacement du serpent au clavier : **flèches directionnelles** et **ZQSD**
- Le serpent grandit en mangeant les pommes
- Détection des collisions : **murs** et **corps du serpent**
- **Score** affiché en temps réel
- **Meilleur score persistant** entre deux sessions (sauvegardé sur disque)
- Écran de fin de partie (**perdu** / **victoire**) avec **rejeu** sur la touche Entrée
- **Grille de taille paramétrable**
- Gestion correcte du demi-tour interdit (le serpent ne peut pas se retourner sur lui-même)

---

## Lancer le jeu

### Prérequis

- **Java 17+** (développé et testé avec Java 23 / 27)
- **Maven** (JavaFX est géré automatiquement comme dépendance, aucune installation
  manuelle du SDK n'est nécessaire)

### Commande

```bash
mvn javafx:run
```

Au premier lancement, Maven télécharge JavaFX et ses dépendances (une à deux minutes),
puis le jeu se lance.

### Contrôles

| Action            | Touches                     |
|-------------------|-----------------------------|
| Déplacer          | Flèches ou `Z` `Q` `S` `D`  |
| Rejouer après une partie | `Entrée`             |

---

## Lancer les tests

Toute la logique de jeu est couverte par des tests unitaires (**JUnit 5**) :

```bash
mvn test
```

---

## Architecture

Le projet suit une séparation stricte **Modèle / Vue / Contrôleur (MVC)**.
La règle directrice : **la logique du jeu ne connaît rien de l'affichage ni du
stockage**. Le modèle peut tourner, et être testé, sans ouvrir la moindre fenêtre.

```
src/main/java/com/williamdesmuliers/snake/
├── Main.java              Point d'entrée : assemble tout, gère la boucle de jeu
├── model/                 Logique pure du jeu (aucune dépendance à JavaFX)
│   ├── Position.java       Une case (x, y), immuable
│   ├── Direction.java      Enum portant son vecteur de déplacement
│   ├── Grid.java           Dimensions du terrain + test des limites
│   ├── Snake.java          Le serpent (ArrayDeque) : avancer, grandir, collisions
│   ├── Apple.java          La pomme : réapparition sur une case libre
│   ├── Game.java           Chef d'orchestre : règles, score, état de partie
│   └── GameState.java      Enum : RUNNING / LOST / WON
├── view/
│   └── GameView.java       Rendu sur un Canvas JavaFX (grille, serpent, pomme, score)
├── control/
│   └── InputController.java Traduction des touches en commandes de jeu
└── storage/
    └── HighScoreRepository.java  Sauvegarde / lecture du meilleur score sur disque
```

### Quelques choix techniques

- **Serpent = `ArrayDeque`** : avancer consiste à ajouter une tête et retirer la
  queue ; manger revient simplement à *ne pas* retirer la queue (croissance « gratuite »).
- **Immutabilité** de `Position`, `Direction` et `Grid` : plus sûr, pas d'effets de
  bord inattendus.
- **Source unique de vérité** : la grille ne stocke pas le contenu du jeu ; le serpent
  et la pomme sont seuls maîtres de leur position.
- **Tampon de direction** (`direction` réelle vs `nextDirection` demandée) pour éviter
  le demi-tour mortel lors d'appuis rapides.
- **Persistance** dans le dossier personnel de l'utilisateur (donc inscriptible,
  contrairement à un fichier embarqué dans le `.jar`).

---

## Technologies

- **Java** 17+
- **JavaFX 27** (interface graphique)
- **Maven** (build et gestion des dépendances)
- **JUnit 5** (tests unitaires)

---

## Ce que ce projet m'a appris

- **Concevoir avant de coder** : poser les responsabilités de chaque classe en amont
  évite énormément de débogage ensuite.
- **L'architecture MVC** et le principe de séparation des responsabilités, concrètement.
- **Les tests unitaires** avec JUnit : tester une logique isolée, dompter l'aléatoire
  (`@RepeatedTest`), tester les exceptions (`assertThrows`), factoriser avec
  `@BeforeEach`, et écrire un test de **non-régression** après avoir corrigé un bug.
- **L'immutabilité** et pourquoi `equals` et `hashCode` vont toujours ensemble.
- **Les enums riches** (portant des données et des méthodes).
- **JavaFX** : le modèle `Stage → Scene → Canvas`, le dessin via `GraphicsContext`,
  la conversion coordonnées de jeu → pixels, et la **boucle de jeu** avec `Timeline`.
- **La programmation événementielle** pour la gestion du clavier.
- **La persistance de fichiers**, ainsi que
  l'**injection de dépendance** pour rendre une classe testable.
- La **gestion des exceptions d'entrée/sortie** (`IOException`) lors de l'accès aux fichiers.
- **Git / GitHub** : commits atomiques avec des messages clairs, travail incrémental.

---

## Améliorations possibles

- Skins / sprites à la place des carrés
- Vitesse croissante avec le score (difficulté progressive)
- Menu de réglages (taille de grille, vitesse)
- Obstacles et nouveaux modes de jeu