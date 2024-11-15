package com.example.ru_pizzaria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

import pizzaria.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreateOrderController {
    private ArrayList<Pizza> pizzas; //dunno if making this static messes with anything
    public Order pizzaOrder;
    private ObservableList<Pizza> pizzaObservableList;

    @FXML
    private Button b_premadePizza;
    @FXML
    private Button b_byoPizza;
    @FXML
    private Button b_addOrder;
    @FXML
    private TableView tv_currentOrder;
    @FXML
    private TableColumn<Pizza, String> styleCol;
    @FXML
    private TableColumn<Pizza, Crust> crustCol;
    @FXML
    private TableColumn<Pizza, ArrayList<Topping>> toppingsCol;
    @FXML
    private TableColumn<Pizza, Double> subtotalCol;

    @FXML
    public void initialize(){

        pizzas = new ArrayList<>();

        createCurrentOrderTV();
//        pizzaObservableList = FXCollections.observableArrayList();
//        if(tv_currentOrder != null)
//            tv_currentOrder.setItems(pizzaObservableList);
    }

    //idk how to get tableview to be populated properly
    @FXML
    protected void createCurrentOrderTV() {
//        if (tv_currentOrder == null) {
//            tv_currentOrder = new TableView<>();
//        }

        styleCol.setCellValueFactory(new PropertyValueFactory<>("style"));
        crustCol.setCellValueFactory(new PropertyValueFactory<>("crust"));
        toppingsCol.setCellValueFactory(new PropertyValueFactory<>("toppings"));
        subtotalCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    protected void onBYOPizzaClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("byo-view.fxml"));

        Parent root = fxmlLoader.load();
        BYOPizzaController byoPizzaController = fxmlLoader.getController();
        byoPizzaController.setOrder(pizzaOrder);
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
        ArrayList<Pizza> pizzas = new ArrayList<>(pizzaOrder.getPizzas());
        //we added the finalized list of pizzas
        for (Pizza pizza : pizzas) {
            newOrder.addPizza(pizza);
//            pizzaObservableList.add(pizza);
        }

        ManageOrdersController.addOrder(newOrder);
        //we prob want to clear the currentOrder tableview upon adding order.
        tv_currentOrder.refresh();
    }

    @FXML
    protected void onRemovePizzaClick() {
        Pizza selectedPizza  = (Pizza) tv_currentOrder.getSelectionModel().getSelectedItem(); //i hope this works
        pizzas.remove(selectedPizza);
        pizzaObservableList.remove(selectedPizza);
    }

    public void addPizza(Pizza pizza) {
        if(pizzaObservableList == null)
            pizzaObservableList = FXCollections.observableArrayList();
//        if(tv_currentOrder == null)
//            tv_currentOrder = new TableView();
        pizzas.add(pizza);
        pizzaObservableList.add(pizza);

        tv_currentOrder.setItems(pizzaObservableList);
        tv_currentOrder.refresh(); //todo: figure out why tableview isn't being populated

        //debugging
        System.out.println("List of pizzas currently");
        System.out.println(pizzas);
    }

    public void setPizzaOrder(Order order){
        this.pizzaOrder = order;
    }
}
