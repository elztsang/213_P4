package com.example.ru_pizzaria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import pizzaria.*;

/**
 * Lets you view all the orders and cancel an order.
 */
public class ManageOrdersController {
    private static ArrayList<Order> pizzaOrders;
    private static ObservableList<Integer> pizzaOrderOptions;
    private static int orderCounter;


    @FXML
    private ListView lv_selectedOrder;
    @FXML
    private Button b_removeOrder;
    @FXML
    private Button b_exportOrder;
    @FXML
    private ComboBox<Integer> cb_orderNumber;
    @FXML
    private TextField tf_orderTotal;
    @FXML
    private TextArea ta_errorLog;

    @FXML
    public void initialize() {
        if (pizzaOrders == null)
            pizzaOrders = new ArrayList<>();

        if (pizzaOrderOptions == null) {
            pizzaOrderOptions = FXCollections.observableArrayList();
            // If we're creating pizzaOrderOptions for the first time and pizzaOrders has data,
            // we need to sync them
            if (!pizzaOrders.isEmpty()) {
                for (Order order : pizzaOrders) {
                    pizzaOrderOptions.add(order.getOrderNumber());
                }
            }
        }

        b_removeOrder.setDisable(cb_orderNumber.getSelectionModel().isEmpty());
        b_exportOrder.setDisable(pizzaOrders.isEmpty());
        cb_orderNumber.setItems(pizzaOrderOptions);
        initOrderSelectionListener();
    }

    private void initOrderSelectionListener() {
        cb_orderNumber.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            b_removeOrder.setDisable(cb_orderNumber.getSelectionModel().isEmpty());
            //update the listview on selection
            if (lv_selectedOrder == null)
                lv_selectedOrder = new ListView<>();

            if (cb_orderNumber.getSelectionModel().getSelectedItem() == null) {
                return;
            }
            int selectedOrderNumber = cb_orderNumber.getSelectionModel().getSelectedItem();
            Order order = findOrderNumber(selectedOrderNumber);
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
            File output = new File("src/main/exported_orders.txt");
            PrintWriter pw = new PrintWriter(output);  // This will automatically overwrite the file

            for (Order order : pizzaOrders) {
                pw.println(order);
            }

            pw.close();
        } catch (IOException e) {
            ta_errorLog.setText("An error occurred when exporting the orders.");
            e.printStackTrace();
        }
    }

    private Order findOrderNumber(int orderNumber) {
        for (Order order : pizzaOrders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }

        return null;
    }

    @FXML
    protected void onRemoveOrderClick() {
        int selectedOrderNumber = cb_orderNumber.getSelectionModel().getSelectedItem();
        Order selectedOrder = findOrderNumber(selectedOrderNumber);

        if (selectedOrder != null) {
            pizzaOrders.remove(selectedOrder);
            pizzaOrderOptions.remove(Integer.valueOf(selectedOrder.getOrderNumber())   );
        } else {
            ta_errorLog.setText("Unable to remove order -- no valid order selected.");
        }

        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
        tf_orderTotal.setText(String.format("$%s", moneyFormat.format(0)));
        lv_selectedOrder.getItems().clear();
        lv_selectedOrder.refresh();

        b_removeOrder.setDisable(cb_orderNumber.getSelectionModel().isEmpty()); // always true
        b_exportOrder.setDisable(pizzaOrders.isEmpty());

        if (cb_orderNumber.getSelectionModel().getSelectedItem() != null) {
            cb_orderNumber.getSelectionModel().clearSelection();
        }

        if (pizzaOrders.isEmpty()) {
            cb_orderNumber.getSelectionModel().clearSelection();
        }
    }

    public int getOrderCounter() {
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
        pizzaOrderOptions.add(order.getOrderNumber());
        orderCounter += 1;
    }
}