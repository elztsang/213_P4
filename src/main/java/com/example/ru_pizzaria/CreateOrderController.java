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
    private static ArrayList<Pizza> pizzas; //dunno if making this static messes with anything

    @FXML
    private Button b_premadePizza;
    @FXML
    private Button b_byoPizza;
    @FXML
    private Button b_addOrder;


    @FXML
    public void initialize(){
        if (pizzas == null) {
            pizzas = new ArrayList<>();
        }
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

    @FXML
    protected void onAddOrderClick() throws IOException {
        Order newOrder = new Order(); // note to self (ron) - this is what i specifically was talking abt in order class

        //we added the finalized list of pizzas
        for (Pizza pizza : pizzas) {
            newOrder.addPizza(pizza);
        }

        ManageOrdersController.addOrder(newOrder);
    }

    public static void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        System.out.println("List of pizzas currently");
        System.out.println(pizzas);
    }
}
