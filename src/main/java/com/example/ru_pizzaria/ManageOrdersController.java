package com.example.ru_pizzaria;

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
    private static int orderCounter = 0;


    @FXML
    private ListView lv_allOrders;
    @FXML
    private ComboBox cb_orderNUmber;
//    @FXML
//    private TableColumn<Order, Integer> orderNumberCol;
//    @FXML
//    private TableColumn<Order, Double> orderTotalCol;
//    @FXML
//    private TableColumn<Order, ArrayList<Pizza>> pizzaCol;
//    @FXML
//    private ComboBox cb_cancelOrder;

    @FXML
    public void initialize(){
//        initTableView();
//        createOrderTableView();
    }

//    private void initTableView() {
//        tv_allOrders = new TableView<>(); //temp
//        tv_allOrders.setFixedCellSize(0); // 0 for dynamically sized rows\
//    }
//
//    @FXML
//    protected void createOrderTableView() {
//        orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
//        pizzaCol.setCellValueFactory(new PropertyValueFactory<>("Pizzas"));
//        orderTotalCol.setCellValueFactory(new PropertyValueFactory<>("OrderTotal"));
//    }

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
        Order selectedOrder = (Order) lv_allOrders.getSelectionModel().getSelectedItem(); //pls work
        if (pizzaOrders.contains(selectedOrder)) {
            pizzaOrders.remove(selectedOrder);
        } else {
            //print error message that order doesn't exist
            System.out.println("No valid order selected"); //- move this somewhere visible
        }
    }

    public static void addOrder(Order order) {
        pizzaOrders.add(order);
        orderCounter++;
        order.setOrderNumber(orderCounter); //should be good enough? - shouldn't repeat even if orders r removed
    }
}