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
    private ToggleGroup pizzaSize;
    @FXML
    private RadioButton rb_smallPizza;
    @FXML
    private RadioButton rb_mediumPizza;
    @FXML
    private RadioButton rb_largePizza;


    @FXML
    public void initialize(){
        rb_chicago.setToggleGroup(pizzaStyle);
        rb_ny.setToggleGroup(pizzaStyle);
        rb_smallPizza.setToggleGroup(pizzaSize);
        rb_mediumPizza.setToggleGroup(pizzaSize);
        rb_largePizza.setToggleGroup(pizzaSize);
    }

    @FXML
    protected void onAddPizzaClick() {

    }
}