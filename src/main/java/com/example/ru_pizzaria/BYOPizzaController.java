package com.example.ru_pizzaria;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import pizzaria.*;

public class BYOPizzaController {

    @FXML
    private ToggleGroup pizzaStyle;
    @FXML
    private RadioButton rb_chicago;
    @FXML
    private RadioButton rb_ny;
    @FXML
    private Button b_addpizza;

    @FXML
    public void initialize() {
        rb_chicago.setToggleGroup(pizzaStyle);
        rb_ny.setToggleGroup(pizzaStyle);
    }

    @FXML
    protected void onAddPizzaClick() {
        //get pizza that we created
        //add pizza
        if (rb_chicago.isSelected()) {
            //create chicago pizza with specified toppings + size
            //need to consider case where some field aren't field and print a message
        }

        if (rb_ny.isSelected()) {
            //create ny pizza with specified toppings + size
        }

        //CreateOrderController.addPizza(pizza);
    }

    //return selection of which pizza style - how to do this?
    private void getPizzaType() {

    }
}
