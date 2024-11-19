package com.example.ru_pizzaria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

import pizzaria.*;

import java.text.DecimalFormat;

public class CreateOrderController {
    private final double SALESTAX = 0.06625; //todo: double check if this is the right value

    private double total = 0.0;
    private Order pizzaOrder;
    private ObservableList<Pizza> pizzaObservableList;

    private ManageOrdersController manageController;

    @FXML
    private ListView lv_currentOrder;
    @FXML
    private Button b_addOrder;
    @FXML
    private TextField tf_total;
    @FXML
    private TextField tf_salestax;
    @FXML
    private TextField tf_ordertotal;
    @FXML
    private TextField tf_orderNumber;
    @FXML
    private TextArea ta_errorLog;

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        pizzaObservableList = FXCollections.observableArrayList();
        updateTotal();
        updateSalesTax();
        updateOrderTotal();

        b_addOrder.setDisable(pizzaObservableList.isEmpty());
    }

    /**
     * Get the reference to the ManageOrdersController object.
     * We can call any public method defined in the controller through the reference.
     * @param controller the controller to assign a reference for.
     */
    public void setManageController(ManageOrdersController controller) {
        manageController = controller;
    }

    /**
     * Helper method to update the total order price.
     */
    private void updateTotal() {
        if (pizzaOrder == null) {
            pizzaOrder = new Order();
        }

        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
        tf_total.setText(String.format("$%s", moneyFormat.format(pizzaOrder.getTotal()))); //String.format("$ %1$,.2f", total)
    }

    /**
     * Helper method to update the sales tax.
     */
    private void updateSalesTax() {
        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");

        tf_salestax.setText(String.format("$%s", moneyFormat.format(pizzaOrder.getSalesTax()))); //String.format("$ %1$,.2f", salesTax)
    }

    /**
     * Helper method to update the order total.
     */
    private void updateOrderTotal() {
        double orderTotal = total + (total * .06625);
        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
        tf_ordertotal.setText(String.format("$%s", moneyFormat.format(pizzaOrder.getOrderTotal()))); //String.format("$ %1$,.2f", orderTotal)
    }

    /**
     * Method that creates a new scene for creating a Build Your Own pizza.
     * @throws IOException
     */
    @FXML
    protected void onBYOPizzaClick() throws IOException {
        if (pizzaOrder.getOrderNumber() < 0) {
            pizzaOrder.setOrderNumber(manageController.getOrderCounter());
            tf_orderNumber.setText(String.valueOf(manageController.getOrderCounter()));
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("byo-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        BYOPizzaController byoPizzaController = fxmlLoader.getController();
        byoPizzaController.setOrderController(this);

        stage.setScene(scene);
        stage.setTitle("BYO Pizzas");
        stage.show();
    }

    /**
     * Method that creates a new scene for creating a premade pizza.
     * @throws IOException
     */
    @FXML
    protected void onPremadePizzaClick() throws IOException {
        if (pizzaOrder.getOrderNumber() < 0) {
            pizzaOrder.setOrderNumber(manageController.getOrderCounter());
            tf_orderNumber.setText(String.valueOf(manageController.getOrderCounter()));
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("premade-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        PremadePizzaController premadePizzaController = fxmlLoader.getController();
        premadePizzaController.setOrderController(this);

        stage.setScene(scene);
        stage.setTitle("Premade Pizzas");
        stage.show();
    }

    /**
     * Method to process adding an order to the order list. If there are no pizzas in the order list, the method will display an error message in the GUI.
     */
    @FXML
    protected void onAddOrderClick() {
        if (pizzaOrder.getOrderNumber() >= 0 || !pizzaObservableList.isEmpty()) {
            manageController.addOrder(pizzaOrder);
            //clear order and reupdate values
            pizzaObservableList.clear();
            lv_currentOrder.setItems(pizzaObservableList);
            lv_currentOrder.refresh();
            pizzaOrder = new Order();
            pizzaOrder.setOrderNumber(manageController.getOrderCounter());
            tf_orderNumber.setText(String.valueOf(manageController.getOrderCounter()));
            updateTotal();
            updateSalesTax();
            updateOrderTotal();
        } else {
            ta_errorLog.setText("Order is empty!");
        }

        b_addOrder.setDisable(pizzaObservableList.isEmpty());
    }

    /**
     * Method to process removing a pizza from the order.
     */
    @FXML
    protected void onRemovePizzaClick() {
        Pizza selectedPizza = (Pizza) lv_currentOrder.getSelectionModel().getSelectedItem(); //i hope this works
        pizzaOrder.getPizzas().remove(selectedPizza);
        pizzaObservableList.remove(selectedPizza);

        updateTotal();
        updateSalesTax();
        updateOrderTotal();
        b_addOrder.setDisable(pizzaObservableList.isEmpty());
    }

    /**
     * Adds a pizza to the list of pizzas in the order. Also refreshes the ListView of pizzas in the order.
     * @param pizza the pizza to add to the order.
     */
    public void addPizza(Pizza pizza) {
        if (pizzaObservableList == null)
            pizzaObservableList = FXCollections.observableArrayList();

        pizzaOrder.addPizza(pizza);
        pizzaObservableList.setAll(pizzaOrder.getPizzas());

        lv_currentOrder.setItems(pizzaObservableList);
        lv_currentOrder.refresh();

        updateTotal();
        updateSalesTax();
        updateOrderTotal();
        b_addOrder.setDisable(false);
    }
}
