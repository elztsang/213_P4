package com.example.ru_pizzaria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Mnemonic;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button b_manageOrders;
    private Button b_makeOrder;

    private FXMLLoader fxmlManageLoader;
    private FXMLLoader fxmlOrderLoader;
    private ManageOrdersController manageController;
    private CreateOrderController orderController;


    @FXML
    public void initialize() {

    }

//    public void setMenuManageController(ManageOrdersController controller){
//        manageController = controller;
//    }
//
//    public void setMenuOrderController(CreateOrderController controller){
//        orderController = controller;
//    }

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
        orderController.setPrimaryStage(stage, scene);
        stage.setTitle("Create an Order");
        stage.setScene(scene);
        stage.show();
    }

//    @FXML
//    protected void setSize() {
//
//    }
}