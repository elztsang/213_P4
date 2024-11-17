package com.example.ru_pizzaria;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pizzaria.*;
/**
 * Lets you view all the orders and cancel an order.
 */
public class ManageOrdersController {
    private ObservableList<Order> pizzaOrderOptions;
    private MainMenuController mainController;
    private Stage primaryStage; //the reference of the main window.
    private Scene primaryScene; //the ref. of the scene set to the primaryStage


    @FXML
    private TableView tv_allOrders;
    @FXML
    private TableColumn<Order, Integer> orderNumberCol;
    @FXML
    private TableColumn<Order, Double> orderTotalCol;
    @FXML
    private TableColumn<Order, ArrayList<Pizza>> pizzaCol;
    @FXML
    private ComboBox cb_cancelOrder;

    @FXML
    public void initialize(){
//        if (pizzaOrders == null)
//            pizzaOrders = new ArrayList<>();
        orderNumberCol = new TableColumn<>();
        orderTotalCol = new TableColumn<>();
        pizzaCol = new TableColumn<>();
        initTableView();
        createOrderTableView();
    }

    private void initTableView() {
        tv_allOrders = new TableView<>(); //temp
        tv_allOrders.setFixedCellSize(0); // 0 for dynamically sized rows\
    }

    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

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
    protected void createOrderTableView() {
        orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
        pizzaCol.setCellValueFactory(new PropertyValueFactory<>("Pizzas"));
        orderTotalCol.setCellValueFactory(new PropertyValueFactory<>("OrderTotal"));
    }

    @FXML
    protected void exportOrders() {
        try {
            File output = new File("exported_orders.txt");
            //taken from example code
            if (output.exists()) {
                //System.out.println("file already exists"); // unnecessary
                System.exit(1);
            }
            PrintWriter pw = new PrintWriter(output);
            for (Order order : mainController.getPizzaOrders()) {
                pw.println(order);
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void onRemoveOrderClick() {
        Order selectedOrder = (Order) tv_allOrders.getSelectionModel().getSelectedItem(); //pls work
        if (mainController.getPizzaOrders().contains(selectedOrder)) {
            mainController.getPizzaOrders().remove(selectedOrder);
        } else {
            //print error message that order doesn't exist
            System.out.println("No valid order selected"); //- move this somewhere visible
        }
    }

//    public void addOrder(Order order) {
//        pizzaOrders.add(order);
//        orderCounter++;
//        order.setOrderNumber(orderCounter); //should be good enough? - shouldn't repeat even if orders r removed
//
//        //todo: debugging
//        System.out.println("ADD: OrderCounter: " + orderCounter);
//        System.out.println("ADD: List of Orders\n" + pizzaOrders);
//    }
}