package com.williamdesmuliers.snake.storage;

import java.nio.file.Files;
import java.nio.file.Path;

public class HighScoreRepository {

    private final Path path;

    public HighScoreRepository() {
        this(Path.of(System.getProperty("user.home"), ".snake_highscore"));
    }

    public HighScoreRepository(Path path) {
        this.path = path;
    }

    public int load() {
        if(!Files.exists(path)){return 0;}
        try{
            String content = Files.readString(path);
            return Integer.parseInt(content.trim());
        }    
        catch (Exception e){
            return 0;
        }
    }

    public void save(int score) {
        try{
            Files.writeString(path, String.valueOf(score));
        }
        catch (Exception e){return;}
    }
}