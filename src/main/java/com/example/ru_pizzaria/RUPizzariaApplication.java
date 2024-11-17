package com.example.ru_pizzaria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RUPizzariaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //TODO: figure out how to load multiple scenes with diff fxmls/controllers
        FXMLLoader fxmlLoader = new FXMLLoader(RUPizzariaApplication.class.getResource("mainmenu-view.fxml"));
//        MainMenuController mainController = fxmlLoader.getController();

//        FXMLLoader fxmlManageLoader = new FXMLLoader(getClass().getResource("manageorders-view.fxml"));
//        fxmlManageLoader.load();
//        ManageOrdersController manageController = fxmlManageLoader.getController();
//
//        FXMLLoader fxmlOrderLoader = new FXMLLoader(getClass().getResource("createorder-view.fxml"));
//        fxmlOrderLoader.load();
//        CreateOrderController orderController = fxmlOrderLoader.getController();

//        mainController.setMenuManageController(manageController);
//        mainController.setMenuOrderController(orderController);
//        orderController.setManageController(manageController);

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("RU Pizzaria Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}