package org.clinic.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClinicApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Загружаем FXML-файл MainView
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/clinic/main-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Устанавливаем сцену
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Clinic Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
