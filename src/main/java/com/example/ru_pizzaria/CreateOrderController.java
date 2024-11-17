package com.example.ru_pizzaria;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

import pizzaria.*;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CreateOrderController {
    private final double SALESTAX = 0.06625; //todo: double check if this is the right value

    public double total = 0.0;
    private MainMenuController mainController;
    //we could delete this and just add directly to order -elz
    private ArrayList<String> styles;
    private Order pizzaOrder;
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
    private Button b_removePizza;
    @FXML
    private ListView lv_currentOrder;
    @FXML
    private TextField tf_total;
    @FXML
    private TextField tf_salestax;
    @FXML
    private TextField tf_ordertotal;

    /**
     * Set the reference of the stage and scene before show()
     * @param stage the stage used to display the scene
     * @param scene the scene set to the stage
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    @FXML
    public void initialize(){
        pizzaObservableList = FXCollections.observableArrayList();

        updateTotal();
        updateSalesTax();
        updateOrderTotal();
    }

    private void updateTotal() {
        if (pizzaOrder == null) {
            pizzaOrder = new Order();
        }

        total = 0;

        for (Pizza pizza : pizzaOrder.getPizzas()) {
            total += pizza.price();
        }

        double curTotal = total;
        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
        tf_total.setText(String.format("$%s", moneyFormat.format(curTotal))); //String.format("$ %1$,.2f", total)
    }

    private void updateSalesTax() {
        double salesTax = total * 0.06625;
        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");

        tf_salestax.setText(String.format("$%s", moneyFormat.format(salesTax))); //String.format("$ %1$,.2f", salesTax)
    }

    private void updateOrderTotal() {
        double orderTotal = total + (total * .06625);
        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
        tf_ordertotal.setText(String.format("$%s", moneyFormat.format(orderTotal))); //String.format("$ %1$,.2f", orderTotal)
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
        }

        mainController.addOrder(newOrder);
        //we prob want to clear the currentOrder tableview upon adding order.
        lv_currentOrder.refresh();
    }

    @FXML
    protected void onRemovePizzaClick() {
        Pizza selectedPizza  = (Pizza) lv_currentOrder.getSelectionModel().getSelectedItem(); //i hope this works
        pizzaOrder.getPizzas().remove(selectedPizza);
        pizzaObservableList.remove(selectedPizza);

        updateTotal();
        updateSalesTax();
        updateOrderTotal();

        //todo: debugging
//        System.out.println("REMOVE: total: " + total);
//        System.out.println("REMOVE: List of pizzas currently");
//        System.out.println(pizzaOrder.getPizzas());
    }

    public void addPizza(Pizza pizza) {
        if(pizzaObservableList == null)
            pizzaObservableList = FXCollections.observableArrayList();

        //pizzas.add(pizza);
        pizzaOrder.addPizza(pizza);
        pizzaObservableList.setAll(pizzaOrder.getPizzas());

        lv_currentOrder.setItems(pizzaObservableList);
        lv_currentOrder.refresh(); //todo: figure out why tableview isn't being populated

        updateTotal();
        updateSalesTax();
        updateOrderTotal();

        //todo: debugging
//        System.out.println("ADD: total: " + total);
//        System.out.println("ADD: List of pizzas currently");
//        System.out.println(pizzaOrder.getPizzas());
    }

    public void setPizzaOrder(Order order){
        this.pizzaOrder = order;
    }
}
