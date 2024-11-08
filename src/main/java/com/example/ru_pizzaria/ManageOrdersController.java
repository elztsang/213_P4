package com.example.ru_pizzaria;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.cell.PropertyValueFactory;
import pizzaria.*;
/**
 * Lets you view all the orders and cancel an order.
 */
public class ManageOrdersController {
    private ArrayList<Order> pizzaOrders;
    private ObservableList<Order> pizzaOrderOptions;


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
        tv_allOrders.setFixedCellSize(0); // 0 for dynamically sized rows\
        createOrderTableView();
    }

    @FXML
    protected void createOrderTableView() {
        orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
        orderTotalCol.setCellValueFactory(new PropertyValueFactory<>("OrderTotal"));
        pizzaCol.setCellValueFactory(new PropertyValueFactory<>("Pizzas"));
    }

    @FXML
    protected void exportOrders() {
        try {
            File output = new File("exported_orders.txt");
            if (output.createNewFile()) {
                //write
            } else {
                //overwrite file?
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}