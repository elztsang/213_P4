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
    private static ObservableList<Order> pizzaOrderOptions;
    private static int orderCounter;


    @FXML
    private ListView lv_selectedOrder;
    @FXML
    private ComboBox<Order> cb_orderNumber;

    @FXML
    public void initialize(){
        if (pizzaOrders == null)
            pizzaOrders = new ArrayList<>();

        if (pizzaOrderOptions == null) {
            pizzaOrderOptions = FXCollections.observableArrayList();
            // If we're creating pizzaOrderOptions for the first time and pizzaOrders has data,
            // we need to sync them
            if (!pizzaOrders.isEmpty()) {
                pizzaOrderOptions.addAll(pizzaOrders);
            }
        }

        cb_orderNumber.setItems(pizzaOrderOptions);
        initOrderSelectionListener();

        debugPrintCollections("initialize");
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
        Order selectedOrder = cb_orderNumber.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            pizzaOrders.remove(selectedOrder);
            pizzaOrderOptions.remove(selectedOrder);
        } else {
            //print error message that order doesn't exist
            System.out.println("No valid order selected"); //- move this somewhere visible
        }
    }

    public int getOrderCounter(){
        return orderCounter;
    }

    public void addOrder(Order order) {
        if (pizzaOrders == null) {
            pizzaOrders = new ArrayList<>();
        }
        if (pizzaOrderOptions == null) {
            pizzaOrderOptions = FXCollections.observableArrayList();
        }

        pizzaOrders.add(order);
        pizzaOrderOptions.add(order);
        orderCounter += 1;
    }

    private void debugPrintCollections(String location) {
        System.out.println("DEBUG " + location);
        System.out.println("pizzaOrders: " + (pizzaOrders == null ? "null" : pizzaOrders));
        System.out.println("pizzaOrderOptions: " + (pizzaOrderOptions == null ? "null" : pizzaOrderOptions));
        System.out.println("ComboBox items: " + cb_orderNumber.getItems());
        System.out.println("-------------------");
    }
}