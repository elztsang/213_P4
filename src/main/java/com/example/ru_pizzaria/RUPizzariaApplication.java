package com.example.ru_pizzaria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RUPizzariaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RUPizzariaApplication.class.getResource("mainmenu-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("RU Pizzaria Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}