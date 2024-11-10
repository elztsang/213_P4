package com.example.ru_pizzaria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

import pizzaria.*;
import java.util.ArrayList;

public class CreateOrderController {
    private ArrayList<Pizza> pizzas;

    @FXML
    private Button b_premadePizza;
    @FXML
    private Button b_byoPizza;
    @FXML
    private Button b_addOrder;
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
        rb_smallPizza.setToggleGroup(pizzaSize);
        rb_mediumPizza.setToggleGroup(pizzaSize);
        rb_largePizza.setToggleGroup(pizzaSize);
    }

    //i think this works? - scene navigation
    @FXML
    protected void onBYOPizzaClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("byo-view.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("BYOPizza");
        stage.show();
    }

    @FXML
    protected void onPremadePizzaClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("premade-view.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("Premade Pizzas");
        stage.show();
    }

    public static void addPizza(Pizza pizza) {

    }
}
