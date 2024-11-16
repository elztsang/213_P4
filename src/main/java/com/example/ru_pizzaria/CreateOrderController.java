package com.example.ru_pizzaria;

import javafx.beans.property.SimpleStringProperty;
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
    //we could delete this and just add directly to order -elz
    private ArrayList<Pizza> pizzas; //dunno if making this static messes with anything
    private ArrayList<String> styles;
    public Order pizzaOrder;
    private ObservableList<Pizza> pizzaObservableList;
    private Stage primaryStage; //the reference of the main window.
    private Scene primaryScene; //the ref. of the scene set to the primaryStage

    @FXML
    private Button b_premadePizza;
    @FXML
    private Button b_byoPizza;
    @FXML
    private Button b_addOrder;
    @FXML
    private ListView lv_currentOrder;
//    @FXML
//    private TableColumn<Pizza, String> styleCol;
//    @FXML
//    private TableColumn<Pizza, Crust> crustCol;
//    @FXML
//    private TableColumn<Pizza, ArrayList<Topping>> toppingsCol;
//    @FXML
//    private TableColumn<Pizza, Double> subtotalCol;

    /**
     * Set the reference of the stage and scene before show()
     * @param stage the stage used to display the scene
     * @param scene the scene set to the stage
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    @FXML
    public void initialize(){

//        pizzas = new ArrayList<>();


        createCurrentOrderLV();
        pizzaObservableList = FXCollections.observableArrayList();

//        if(tv_currentOrder != null)
//            tv_currentOrder.setItems(pizzaObservableList);
    }

    //idk how to get tableview to be populated properly
    @FXML
    protected void createCurrentOrderLV() {
//        styleCol.setCellValueFactory(cellData ->
//                new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
//        crustCol.setCellValueFactory(new PropertyValueFactory<>("crust"));
//        toppingsCol.setCellValueFactory(new PropertyValueFactory<>("toppings"));
//        subtotalCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    protected void onBYOPizzaClick() throws IOException {
        if(pizzaOrder == null)
            pizzaOrder = new Order();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("byo-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        BYOPizzaController byoPizzaController = fxmlLoader.getController();
        byoPizzaController.setOrderController(this);

        stage.setScene(scene);
        stage.setTitle("BYO Pizzas");
        stage.show();
    }

    @FXML
    protected void onPremadePizzaClick() throws IOException {
        if(pizzaOrder == null)
            pizzaOrder = new Order();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("premade-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        PremadePizzaController premadePizzaController = fxmlLoader.getController();
        premadePizzaController.setOrderController(this);

        stage.setScene(scene);
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
        lv_currentOrder.refresh();
    }

    @FXML
    protected void onRemovePizzaClick() {
        Pizza selectedPizza  = (Pizza) lv_currentOrder.getSelectionModel().getSelectedItem(); //i hope this works
        pizzas.remove(selectedPizza);
        pizzaObservableList.remove(selectedPizza);
    }

    public void addPizza(Pizza pizza) {
        if(pizzaObservableList == null)
            pizzaObservableList = FXCollections.observableArrayList();
//        if(tv_currentOrder == null)
//            tv_currentOrder = new TableView();
//        pizzas.add(pizza);
        pizzaOrder.addPizza(pizza);
        pizzaObservableList.setAll(pizzaOrder.getPizzas());

        lv_currentOrder.setItems(pizzaObservableList);
        lv_currentOrder.refresh(); //todo: figure out why tableview isn't being populated

        //debugging
        System.out.println("List of pizzas currently");
        System.out.println(pizzas);
    }

    public void setPizzaOrder(Order order){
        this.pizzaOrder = order;
    }
}
