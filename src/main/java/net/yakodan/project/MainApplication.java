package net.yakodan.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("app.fxml"));
        fxmlLoader.setRoot(new AnchorPane());
        Scene scene = new Scene(fxmlLoader.load());
        this.stage = stage;
        this.stage.setTitle("Проект");
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}