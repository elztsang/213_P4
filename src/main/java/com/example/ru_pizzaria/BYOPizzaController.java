package com.example.ru_pizzaria;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;
import pizzaria.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BYOPizzaController {
    private final static double SMALL_PRICE = 8.99; //todo: check if these r the right prices
    private final static double MEDIUM_PRICE = 10.99;
    private final static double LARGE_PRICE = 12.99;

    private CreateOrderController orderController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;

    @FXML
    private ToggleGroup pizzaStyle;
    @FXML
    private ToggleGroup pizzaSize;
    @FXML
    private TextField tf_pizzaPriceOut;
    @FXML
    private RadioButton rb_smallPizza;
    @FXML
    private RadioButton rb_mediumPizza;
    @FXML
    private RadioButton rb_largePizza;
    @FXML
    private RadioButton rb_chicago;
    @FXML
    private RadioButton rb_ny;
    @FXML
    private Button b_addpizza;
    @FXML
    private ListView<Topping> lv_byoToppings;
    @FXML
    private TextArea ta_byoToppings;

    @FXML
    public void initialize() {
        initPizzaStyleTG();
        initPizzaSizeTG();
//        pizzaSize.selectToggle(rb_mediumPizza); //have a default value for size+price
//        pizzaStyle.selectToggle(rb_chicago);
        setPizzaInitPrice();
        initToppingsLV();
        initSubtotalListener();
    }

    public void setOrderController(CreateOrderController controller) {
        orderController = controller;
    }

    private void initSubtotalListener() {
        lv_byoToppings.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //todo: figure out how to fix the issue below, or leave it cause it likely is an issue of how listview updates values
            //also this doesn't update sometimes for soem reason
            //to replicate - try selecting 3 items, then unselect 1 item -> only updates when clicking(without alt click)

            ObservableList<Topping> toppingsList = lv_byoToppings.getSelectionModel().getSelectedItems();
            DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");

            double toppingSubtotal = toppingsList.size() * 1.69;
            double pizzaSubtotal = getPizzaSizePrice();
            double orderTotal = pizzaSubtotal + toppingSubtotal;
            tf_pizzaPriceOut.setText(String.format("$%s", moneyFormat.format(orderTotal)));
            ta_byoToppings.setText(toppingsList.toString());
        });

        pizzaSize.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<Topping> toppingsList = lv_byoToppings.getSelectionModel().getSelectedItems();
            DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");

            double toppingSubtotal = toppingsList.size() * 1.69;
            double pizzaSubtotal = getPizzaSizePrice();
            double orderTotal = pizzaSubtotal + toppingSubtotal;
            tf_pizzaPriceOut.setText(String.format("$%s", moneyFormat.format(orderTotal)));
        });
    }

    private double getPizzaSizePrice(){
        if (rb_smallPizza.isSelected()) {
            return SMALL_PRICE;
        } else if (rb_mediumPizza.isSelected()) {
            return MEDIUM_PRICE;
        } else if (rb_largePizza.isSelected()) {
            return LARGE_PRICE;
        }

        return 0.0;
    }

    private void setPizzaInitPrice() {
        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
        tf_pizzaPriceOut.setText(String.format("$%s", moneyFormat.format(0)));
    }

    private void initToppingsLV() {
        if (lv_byoToppings == null) {
            lv_byoToppings = new ListView<>();
        }

        ObservableList<Topping> toppingOptions = FXCollections.observableArrayList(Topping.values());

        lv_byoToppings.setItems(toppingOptions);
        //weird caveat with this - i need to press cmd in order to select multiple items.
        lv_byoToppings.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //javadoc says to do this if we want multiple selections - ron
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

    private ArrayList<Topping> getSelectedToppings() {
        ObservableList<Topping> selectedToppings = lv_byoToppings.getSelectionModel().getSelectedItems();
        ArrayList<Topping> toppings = new ArrayList<>(selectedToppings);
        if (toppings.size() <= 7) return toppings;
        return null;
    }

    /*
    some considerations to make later
    - ensure there are >=7 toppings selected
    - ensure size option is selected
    - display 13 toppings
     */
    @FXML
    protected void onAddPizzaClick() throws IOException {
        ArrayList<Topping> selectedToppings;
        selectedToppings = getSelectedToppings();
        Pizza pizza = null;

        if (selectedToppings == null) {
            //print error message like "too many toppings selected"
            System.out.println("Please select 7 toppings at most."); // move to area visible to user
            return;
        }
        if (rb_chicago.isSelected()) {
            //create chicago pizza with specified toppings + size
            PizzaFactory chicagoStyle = new ChicagoPizza();
            pizza = chicagoStyle.createBuildYourOwn();
        }

        if (rb_ny.isSelected()) {
            //create ny pizza with specified toppings + size
            PizzaFactory nyStyle = new NYPizza();
            pizza = nyStyle.createBuildYourOwn();
        }

        if (pizza != null) {
            if (pizzaSize.getSelectedToggle() == null) {
                //print error message like "please select a size"
                System.out.println("Please select size"); //move this to a visible area for user
                return;
            }
            pizza.setToppings(selectedToppings);
            String size = ((RadioButton) pizzaSize.getSelectedToggle()).getText();
            pizza.setSize(Size.valueOf(size.toUpperCase())); //get selection

            orderController.addPizza(pizza);
        } else {
            System.out.println("Pizza null"); //change error message
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
