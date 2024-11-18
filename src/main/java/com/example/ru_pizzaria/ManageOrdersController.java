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
import java.text.DecimalFormat;
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
    private TextField tf_orderTotal;

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

        //debugPrintCollections("initialize");
    }

    private void  initOrderSelectionListener() {
        //need to listen for when order is selected and when order is removed -latter done in onremoveorderclick or whatever
        cb_orderNumber.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //update the listview on selection
            if (lv_selectedOrder == null)
                lv_selectedOrder = new ListView<>();

            Order order = (Order) cb_orderNumber.getSelectionModel().getSelectedItem();
            if (order == null) {
                return;
            }
            ObservableList<Pizza> orderPizzaList = FXCollections.observableArrayList(order.getPizzas());
            DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
            double orderTotal = order.getOrderTotal();
            tf_orderTotal.setText(String.format("$%s", moneyFormat.format(orderTotal)));
            lv_selectedOrder.setItems(orderPizzaList);
            lv_selectedOrder.refresh();
        });
    }

    @FXML
    protected void exportOrders() {
        try {
            File output = new File("exported_orders.txt");
            PrintWriter pw = new PrintWriter(output);  // This will automatically overwrite the file

            for (Order order : pizzaOrders) {
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

        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
        tf_orderTotal.setText(String.format("$%s", moneyFormat.format(0)));
        lv_selectedOrder.getItems().clear();
        lv_selectedOrder.refresh();
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
}