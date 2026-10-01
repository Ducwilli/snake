package com.williamdesmuliers.snake;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private Parent createContent() {
        return new StackPane(new Text("Snake"));
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 600, 600));
        stage.setTitle("Snake");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}