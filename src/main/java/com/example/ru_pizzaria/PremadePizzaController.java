package com.example.ru_pizzaria;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.stage.Stage;
import pizzaria.*;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Lets you view all the orders and cancel an order.
 */
public class PremadePizzaController {
    private Order pizzaOrder;
    private Pizza currentPizza;

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
//        pizzaSize.selectToggle(rb_mediumPizza); //have a default value for size+price+type
//        pizzaStyle.selectToggle(rb_chicago);
//        cb_pizzaType.getSelectionModel().selectFirst();
        setPizzaInitPrice();
        initPizzaDetailsListener();

        cb_pizzaType.getItems().addAll("Deluxe", "BBQ Chicken", "Meatzza");

        if(pizzaOrder == null)
            pizzaOrder = new Order();
    }

    private void setPizzaInitPrice() {
        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
        tf_pizzaPriceOut.setText(String.format("$%s", moneyFormat.format(0)));
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

    private void initPizzaDetailsListener() {
        //listen for updates to pizza style, size, type - updates pizza and toppings list prob
        pizzaStyle.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            updatePizza();
            setPizzaSubtotal();
        });

        pizzaSize.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            updatePizza();
            setPizzaSubtotal();
        });

        cb_pizzaType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updatePizza();
            setPizzaSubtotal();
        });
    }

    private void updatePizza() {
        if (rb_chicago.isSelected())
            currentPizza = premadeChicagoTypeSelected();
        if (rb_ny.isSelected())
            currentPizza = premadeNYTypeSelected();

        if (currentPizza != null) {
            if (pizzaSize.getSelectedToggle() != null) {
                String size = ((RadioButton) pizzaSize.getSelectedToggle()).getText();
                currentPizza.setSize(Size.valueOf(size.toUpperCase()));
            }
        }
    }

    private void setPizzaSubtotal() {
        if (currentPizza != null) {
            DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
            double orderTotal = currentPizza.price();
            tf_pizzaPriceOut.setText(String.format("$%s", moneyFormat.format(orderTotal)));
        }
    }

    private double getPizzaSizePrice() {
        double price = 0.0;
        Pizza pizza = null;
        if (rb_chicago.isSelected()) {
            PizzaFactory pizzaFactory = new ChicagoPizza();
            pizza = pizzaFactory.createBuildYourOwn();
        } else if (rb_ny.isSelected()) {
            PizzaFactory pizzaFactory = new ChicagoPizza();
            pizza = pizzaFactory.createBuildYourOwn();
        }

        if (pizza == null) {
            return 0; //figure out way to return error? dunno
        }

        return price;
    }

    private ObservableList<Topping> getPizzaToppings() {
        return null;
    }

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
        if(!cb_pizzaType.getSelectionModel().isEmpty()) {
            if (cb_pizzaType.getValue().equals("BBQ Chicken")) {
                return nyStyle.createBBQChicken();
            } else if (cb_pizzaType.getValue().equals("Deluxe")) {
                return nyStyle.createDeluxe();
            } else if (cb_pizzaType.getValue().equals("Meatzza")) {
                return nyStyle.createMeatzza();
            }
        }
        return null;
    }

    @FXML
    protected void onAddPizzaClick() throws IOException {
        if (currentPizza != null) {
            if (pizzaSize.getSelectedToggle() == null) { // maybe replace with currentPizza.getSize()?
                //print error message like "please select a size"
                System.out.println("Please select size"); //move this to a visible area for user
                return;
            }

            orderController.addPizza(currentPizza);
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