package com.example.ru_pizzaria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pizzaria.Order;
import pizzaria.Pizza;

import java.io.IOException;
import java.util.ArrayList;

public class MainMenuController {
    private ArrayList<Order> pizzaOrders;
    private int orderCounter;

    @FXML
    private Button b_manageOrders;
    private Button b_makeOrder;

    @FXML
    public void initialize(){
        if (pizzaOrders == null) {
            pizzaOrders = new ArrayList<>();
        }
    }

    @FXML
    protected void onManageOrdersButtonClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manageorders-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        ManageOrdersController manageOrdersController = fxmlLoader.getController();
        manageOrdersController.setMainController(this);
        manageOrdersController.setPrimaryStage(stage, scene);

        stage.setTitle("Manage Orders");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onMakeOrderButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createorder-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        CreateOrderController orderController = fxmlLoader.getController();
        orderController.setMainController(this);
        orderController.setPrimaryStage(stage, scene);

        stage.setTitle("Create an Order");
        stage.setScene(scene);
        stage.show();
    }

    public void addOrder(Order order) {
        pizzaOrders.add(order);
        orderCounter++;
        order.setOrderNumber(orderCounter); //should be good enough? - shouldn't repeat even if orders r removed

        //todo: debugging
        System.out.println("ADD: OrderCounter: " + orderCounter);
        System.out.println("ADD: List of Orders\n" + pizzaOrders);
    }

    public ArrayList<Order> getPizzaOrders() {
        return pizzaOrders;
    }

//    @FXML
//    protected void onAddOrderClick() throws IOException {
//        Order newOrder = new Order(); // note to self (ron) - this is what i specifically was talking abt in order class
//        ArrayList<Pizza> pizzas = new ArrayList<>(pizzaOrder.getPizzas());
//        //we added the finalized list of pizzas
//        for (Pizza pizza : pizzas) {
//            newOrder.addPizza(pizza);
//        }
//
//        ManageOrdersController.addOrder(newOrder);
//        //we prob want to clear the currentOrder tableview upon adding order.
//        lv_currentOrder.refresh();
//    }

//    @FXML
//    protected void setSize() {
//
//    }
}