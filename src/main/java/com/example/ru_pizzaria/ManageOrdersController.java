package com.example.ru_pizzaria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.scene.control.cell.PropertyValueFactory;
import pizzaria.*;
/**
 * Lets you view all the orders and cancel an order.
 */
public class ManageOrdersController {
    private static ArrayList<Order> pizzaOrders;
    private ObservableList<Order> pizzaOrderOptions;
    private int orderCounter = 0;


    @FXML
    private ListView lv_selectedOrder;
    @FXML
    private ComboBox cb_orderNumber;

    @FXML
    public void initialize(){
        pizzaOrders = new ArrayList<>();
        pizzaOrderOptions = FXCollections.observableArrayList();
        cb_orderNumber = new ComboBox<>();
        initOrderSelectionListener();
//        initTableView();
//        createOrderTableView();
    }

    private void  initOrderSelectionListener() {
        cb_orderNumber.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //update the listview on selection
            Order order = (Order) cb_orderNumber.getSelectionModel().getSelectedItem();

            ObservableList<Pizza> orderPizzaList = FXCollections.observableArrayList(order.getPizzas());
            lv_selectedOrder.setItems(orderPizzaList);
            lv_selectedOrder.refresh();
        });
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
            for (Order order : pizzaOrders) {
                //todo: debug
                System.out.println("attempting to print: " + order);
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
        Order selectedOrder = (Order) cb_orderNumber.getSelectionModel().getSelectedItem(); //todo: rework this
        if (pizzaOrders.contains(selectedOrder)) {
            pizzaOrders.remove(selectedOrder);
        } else {
            //print error message that order doesn't exist
            System.out.println("No valid order selected"); //- move this somewhere visible
        }
    }

    public int getOrderCounter(){
        return orderCounter;
    }

    public void addOrder(Order order) {
        if (pizzaOrderOptions == null)
            pizzaOrderOptions = FXCollections.observableArrayList();

        pizzaOrders.add(order);
        pizzaOrderOptions.add(order);

        orderCounter++;

        cb_orderNumber.setItems(pizzaOrderOptions);

        //todo: debug
        System.out.println("ADD: OrderCounter: " + orderCounter);
        System.out.println("ADD: Current Observable List\n" + pizzaOrderOptions);
        System.out.println("ADD: Current List of Orders\n" + pizzaOrders);
    }
}