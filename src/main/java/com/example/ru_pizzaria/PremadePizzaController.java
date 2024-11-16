package com.example.ru_pizzaria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.stage.Stage;
import pizzaria.*;

import java.io.IOException;

/**
 * Lets you view all the orders and cancel an order.
 */
public class PremadePizzaController {
    private Order pizzaOrder;
    private CreateOrderController orderController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;


    @FXML
    private Button b_back;
    @FXML
    private TextField tf_pizzaPriceOut;
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
    private ComboBox cb_pizzaType;


    @FXML
    public void initialize(){
        //style
        initPizzaStyleTG();
        //size
        initPizzaSizeTG();
        //pizzatype
//        initPizzaTypeTG();

        cb_pizzaType.getItems().addAll("Deluxe", "BBQ Chicken", "Meatzza");

        if(pizzaOrder == null)
            pizzaOrder = new Order();
    }

    public void setOrderController(CreateOrderController controller) {
        orderController = controller;
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

//    private void initPizzaTypeTG() {
//        pizzaType = new ToggleGroup();
//        rb_bbqchicken.setToggleGroup(pizzaType);
//        rb_deluxe.setToggleGroup(pizzaType);
//        rb_meatzza.setToggleGroup(pizzaType);
//    }

    @FXML
    protected Pizza premadeChicagoTypeSelected(){
        PizzaFactory chicagoStyle = new ChicagoPizza();
        if (cb_pizzaType.getValue().equals("BBQ Chicken")) {
            return chicagoStyle.createBBQChicken();
        } else if (cb_pizzaType.getValue().equals("Deluxe")) {
            return chicagoStyle.createDeluxe();
        } else if (cb_pizzaType.getValue().equals("Meatzza")) {
            return chicagoStyle.createMeatzza();
        }
        return null;
    }

    @FXML
    protected Pizza premadeNYTypeSelected(){
        PizzaFactory nyStyle = new NYPizza();
        if (cb_pizzaType.getValue().equals("BBQ Chicken")) {
            return nyStyle.createBBQChicken();
        } else if (cb_pizzaType.getValue().equals("Deluxe")) {
            return nyStyle.createDeluxe();
        } else if (cb_pizzaType.getValue().equals("Meatzza")) {
            return nyStyle.createMeatzza();
        }
        return null;
    }

    @FXML
    protected void onAddPizzaClick() throws IOException {
        Pizza pizza = null;
        if (rb_chicago.isSelected())
            pizza = premadeChicagoTypeSelected();

        if (rb_ny.isSelected())
            //create ny pizza with specified toppings + size
            pizza = premadeNYTypeSelected();

        if (pizza != null) {
            if (pizzaSize.getSelectedToggle() == null) {
                //print error message like "please select a size"
                System.out.println("Please select size"); //move this to a visible area for user
                return;
            }
            String size = ((RadioButton) pizzaSize.getSelectedToggle()).getText();
            pizza.setSize(Size.valueOf(size.toUpperCase())); //get selection
            orderController.addPizza(pizza);
//                primaryStage.setScene(primaryScene);
//                primaryStage.show();
        } else {
            System.out.println("Please select pizza type");
            //print error message to somewhere visible for customer/employee
        }
    }

    @FXML
    /**
     * Navigate back to the main view.
     */
    public void displayMain() {
        //stage.close(); //close the window.
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
}