package com.example.ru_pizzaria;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Lets you view all the orders and cancel an order.
 */
public class ManageOrdersController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void setSize() {

    }
}