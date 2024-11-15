package com.example.ru_pizzaria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import pizzaria.*;

import java.io.IOException;

/**
 * Lets you view all the orders and cancel an order.
 */
public class PremadePizzaController {
    private Order pizzaOrder;

    @FXML
    private Button b_back;

    @FXML
    private ToggleGroup pizzaStyle;
    @FXML
    private RadioButton rb_chicago;
    @FXML
    private RadioButton rb_ny;
    @FXML
    private Button b_addpizza;
    @FXML
    private ToggleGroup pizzaSize;
    @FXML
    private RadioButton rb_smallPizza;
    @FXML
    private RadioButton rb_mediumPizza;
    @FXML
    private RadioButton rb_largePizza;

    //made assumptions about what we would be using for this - change in the future if necessary
    @FXML
    private ToggleGroup pizzaType;
    @FXML
    private RadioButton rb_bbqchicken;
    @FXML
    private RadioButton rb_deluxe;
    @FXML
    private RadioButton rb_meatzza;


    @FXML
    public void initialize(){
        //style
        initPizzaStyleTG();
        //size
        initPizzaSizeTG();
        //pizzatype
        initPizzaTypeTG();

        if(pizzaOrder == null)
            pizzaOrder = new Order();
    }

    private void initPizzaStyleTG() {
        pizzaStyle = new ToggleGroup();
        rb_chicago.setToggleGroup(pizzaStyle);
        rb_ny.setToggleGroup(pizzaStyle);
    }

    private void initPizzaSizeTG() {
        pizzaSize = new ToggleGroup();
        rb_smallPizza.setToggleGroup(pizzaSize);
        rb_mediumPizza.setToggleGroup(pizzaSize);
        rb_largePizza.setToggleGroup(pizzaSize);
    }

    private void initPizzaTypeTG() {
        pizzaType = new ToggleGroup();
        rb_bbqchicken.setToggleGroup(pizzaType);
        rb_deluxe.setToggleGroup(pizzaType);
        rb_meatzza.setToggleGroup(pizzaType);
    }

    @FXML
    protected void onAddPizzaClick() throws IOException {
        if (rb_chicago.isSelected()) {
            //create chicago pizza with specified toppings + size
            PizzaFactory chicagoStyle = new ChicagoPizza();
            Pizza pizza;
            if (rb_bbqchicken.isSelected()) {
                pizza = chicagoStyle.createBBQChicken();
            } else if (rb_deluxe.isSelected()) {
                pizza = chicagoStyle.createDeluxe();
            } else if (rb_meatzza.isSelected()) {
                pizza = chicagoStyle.createMeatzza();
            } else {
                pizza = null;
            }

            if (pizza != null) {
                if (pizzaSize.getSelectedToggle() == null) {
                    //print error message like "please select a size"
                    System.out.println("Please select size"); //move this to a visible area for user
                    return;
                }
                String size = ((RadioButton) pizzaSize.getSelectedToggle()).getText();
                pizza.setSize(Size.valueOf(size.toUpperCase())); //get selection

                FXMLLoader loader = new FXMLLoader(getClass().getResource("createorder-view.fxml"));
                Parent root = loader.load();
                CreateOrderController createOrderController = loader.getController();

                pizzaOrder.addPizza(pizza);
            } else {
                System.out.println("Please select pizza type");
                //print error message to somewhere visible for customer/employee
            }
        }

        if (rb_ny.isSelected()) {
            //create ny pizza with specified toppings + size
            PizzaFactory nyStyle = new NYPizza();
            Pizza pizza;
            if (rb_bbqchicken.isSelected()) {
                pizza = nyStyle.createBBQChicken();
            } else if (rb_deluxe.isSelected()) {
                pizza = nyStyle.createDeluxe();
            } else if (rb_meatzza.isSelected()) {
                pizza = nyStyle.createMeatzza();
            } else {
                pizza = null;
            }

            if (pizza != null) {
                if (pizzaSize.getSelectedToggle() == null) {
                    //print error message like "please select a size"
                    System.out.println("Please select size"); //move this to a visible area for user
                    return;
                }
                String size = ((RadioButton) pizzaSize.getSelectedToggle()).getText();
                pizza.setSize(Size.valueOf(size.toUpperCase())); //get selection

                FXMLLoader loader = new FXMLLoader(getClass().getResource("createorder-view.fxml"));
                Parent root = loader.load();
                CreateOrderController createOrderController = loader.getController();

                pizzaOrder.addPizza(pizza);
            } else {
                System.out.println("Please select pizza type");
                //print error message to somewhere visible for customer/employee
            }
        }
    }
}