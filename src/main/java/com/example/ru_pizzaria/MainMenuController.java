package com.example.ru_pizzaria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button b_manageOrders;
    private Button b_makeOrder;

    @FXML
    public void initialize(){

    }

    @FXML
    protected void onManageOrdersButtonClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manageorders-view.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("Manage Orders");
        stage.show();
    }

    @FXML
    protected void onMakeOrderButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createorder-view.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("Create an Order");
        stage.show();
    }

//    @FXML
//    protected void setSize() {
//
//    }
}