package com.example.ru_pizzaria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pizzaria.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The controller for the view to create a Build Your Own pizza.
 * @author Elizabeth Tsang, Ron Chrysler Amistad
 */
public class BYOPizzaController {
    private final static double SMALL_PRICE = 8.99; //todo: check if these r the right prices
    private final static double MEDIUM_PRICE = 10.99;
    private final static double LARGE_PRICE = 12.99;
    private final static double TOPPING_PRICE = 1.69;
    private final static int MAX_NUM_TOPPINGS = 7;

    private CreateOrderController orderController;

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
    private ListView<Topping> lv_byoToppings;
    @FXML
    private TextArea ta_byoToppings;
    @FXML
    private ImageView iv_byoImage;
    @FXML
    private Image chicagoBYO;
    @FXML
    private Image nyBYO;
    @FXML
    private TextField tf_crustType;
    @FXML
    private TextArea ta_errorLog;

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        initPizzaStyleTG();
        initPizzaSizeTG();
        setPizzaInitPrice();
        initToppingsLV();
        initSubtotalListener();
        initPizzaStyleListener();
        chicagoBYO = new Image(getClass().getResourceAsStream("/images/chicagoBYO.jpg"));
        nyBYO = new Image(getClass().getResourceAsStream("/images/nyBYO.jpg"));
    }

    /**
     * Get the reference to the CreateOrderController object.
     * We can call any public method defined in the controller through the reference.
     * @param controller the controller to assign a reference for.
     */
    public void setOrderController(CreateOrderController controller) {
        orderController = controller;
    }

    /**
     *  Initializes the event listener for the pizzaStyle toggle group.
     */
    private void initPizzaStyleListener() {
        pizzaStyle.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (rb_chicago.isSelected()) {
                //create chicago pizza with specified toppings + size
                iv_byoImage.setImage(chicagoBYO);
                tf_crustType.setText(Crust.PAN.toString());
            }

            if (rb_ny.isSelected()) {
                //create ny pizza with specified toppings + size
                iv_byoImage.setImage(nyBYO);
                tf_crustType.setText(Crust.HANDTOSSED.toString());
            }
        });
    }

    /**
     * Initializes the event listeners for processing a running subtotal for the pizza.
     */
    private void initSubtotalListener() {
        lv_byoToppings.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<Topping> toppingsList = lv_byoToppings.getSelectionModel().getSelectedItems();
            DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");

            double toppingSubtotal = toppingsList.size() * TOPPING_PRICE;
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
            ta_byoToppings.setText(toppingsList.toString());
        });
    }

    /**
     *
     * @return the price of the pizza determined by its size.
     */
    private double getPizzaSizePrice() {
        if (rb_smallPizza.isSelected()) {
            return SMALL_PRICE;
        } else if (rb_mediumPizza.isSelected()) {
            return MEDIUM_PRICE;
        } else if (rb_largePizza.isSelected()) {
            return LARGE_PRICE;
        }

        return 0.0;
    }

    /**
     * Sets the initial price of the pizza to a default value of 0.00.
     */
    private void setPizzaInitPrice() {
        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
        tf_pizzaPriceOut.setText(String.format("$%s", moneyFormat.format(0)));
    }

    /**
     * Initializes the data in the list view of toppings.
     */
    private void initToppingsLV() {
        if (lv_byoToppings == null) {
            lv_byoToppings = new ListView<>();
        }

        ObservableList<Topping> toppingOptions = FXCollections.observableArrayList(Topping.values());

        lv_byoToppings.setItems(toppingOptions);
        //weird caveat with this - i need to press cmd in order to select multiple items.
        lv_byoToppings.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //javadoc says to do this if we want multiple selections - ron
    }

    /**
     * Initializes the toggle group assignments for the pizza style radio buttons,
     */
    private void initPizzaStyleTG() {
        pizzaStyle = new ToggleGroup();
        rb_chicago.setToggleGroup(pizzaStyle);
        rb_ny.setToggleGroup(pizzaStyle);
    }

    /**
     * Initializes the toggle group assignments for the pizza size radio buttons.
     */
    private void initPizzaSizeTG() {
        pizzaSize = new ToggleGroup();
        rb_smallPizza.setToggleGroup(pizzaSize);
        rb_mediumPizza.setToggleGroup(pizzaSize);
        rb_largePizza.setToggleGroup(pizzaSize);
    }

    /**
     *
     * @return a list of the toppings selected from the list view if the number of toppings selected does not exceed MAX_NUM_TOPPINGS, null otherwise.
     */
    private ArrayList<Topping> getSelectedToppings() {
        ObservableList<Topping> selectedToppings = lv_byoToppings.getSelectionModel().getSelectedItems();
        ArrayList<Topping> toppings = new ArrayList<>(selectedToppings);
        if (toppings.size() <= MAX_NUM_TOPPINGS) return toppings;
        return null;
    }

    /**
     * Method to process adding a pizza to the order.
     * Checks if all the parameters required for a pizza are valid before adding.
     * If there are any missing or invalid parameters, the method will specify the error in the output text in the GUI.
     */
    @FXML
    protected void onAddPizzaClick(){
        ArrayList<Topping> selectedToppings;
        selectedToppings = getSelectedToppings();
        Pizza pizza = null;

        if (selectedToppings == null) {
            ta_errorLog.setText("Please select 7 toppings at most.");
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
                ta_errorLog.setText("Please select a pizza size.");
                return;
            }
            pizza.setToppings(selectedToppings);
            String size = ((RadioButton) pizzaSize.getSelectedToggle()).getText();
            pizza.setSize(Size.valueOf(size.toUpperCase())); //get selection

            orderController.addPizza(pizza);
        } else {
            ta_errorLog.setText("Please fill out all pizza details.");
        }
    }
}
