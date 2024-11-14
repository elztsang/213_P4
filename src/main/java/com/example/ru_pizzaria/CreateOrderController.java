package com.example.ru_pizzaria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

import pizzaria.*;

import java.lang.reflect.Array;
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
    private static TableView tv_currentOrder;
    @FXML
    private TableColumn<String, Pizza> styleCol;
    @FXML
    private TableColumn<Crust, Pizza> crustCol;
    @FXML
    private TableColumn<ArrayList<Topping>, Pizza> toppingsCol;
    @FXML
    private TableColumn<Double, Pizza> subtotalCol;

    @FXML
    public void initialize(){
        if (pizzas == null) {
            pizzas = new ArrayList<>();
        }

        createCurrentOrderTV();
    }

    //idk how to get tableview to be populated properly
    @FXML
    protected void createCurrentOrderTV() {
        if (tv_currentOrder == null) {
            tv_currentOrder = new TableView<>();
        }

        styleCol.setCellValueFactory(new PropertyValueFactory<>("style"));
        crustCol.setCellValueFactory(new PropertyValueFactory<>("crust"));
        toppingsCol.setCellValueFactory(new PropertyValueFactory<>("toppings"));
        subtotalCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

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
        //we prob want to clear the currentOrder tableview upon adding order.
        tv_currentOrder.refresh();
    }

    @FXML
    protected void onRemovePizzaClick() {
        Pizza selectedPizza  = (Pizza) tv_currentOrder.getSelectionModel().getSelectedItem(); //i hope this works
        pizzas.remove(selectedPizza);
    }

    public static void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        tv_currentOrder.refresh(); //todo: figure out why tableview isn't being populated

        //debugging
        System.out.println("List of pizzas currently");
        System.out.println(pizzas);
    }
}
