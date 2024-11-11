package com.example.ru_pizzaria;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Lets you view all the orders and cancel an order.
 */
public class PremadePizzaController {
    @FXML
    private Button b_back;

    @FXML
    private ToggleGroup pizzaStyle;
    @FXML
    private RadioButton rb_chicago;
    @FXML
    private RadioButton rb_ny;
    @FXML
    private Button b_addpizza;

    @FXML
    public void initialize(){
        rb_chicago.setToggleGroup(pizzaStyle);
        rb_ny.setToggleGroup(pizzaStyle);
    }

    @FXML
    protected void onAddPizzaClick() {

    }
}