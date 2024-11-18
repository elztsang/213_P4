package com.example.ru_pizzaria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public void initialize() {

    }

    @FXML
    protected void onManageOrdersButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manageorders-view.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("Manage Orders");
        stage.show();
    }

    @FXML
    protected void onMakeOrderButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createorder-view.fxml"));
        Parent root = fxmlLoader.load();
        CreateOrderController orderController = fxmlLoader.getController();

        FXMLLoader fxmlLoaderManage = new FXMLLoader(getClass().getResource("manageorders-view.fxml"));
        Parent rootManage = fxmlLoaderManage.load();
        ManageOrdersController manageController = fxmlLoaderManage.getController();

        orderController.setManageController(manageController);

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Create an Order");
        stage.setScene(scene);
        stage.show();
    }
}