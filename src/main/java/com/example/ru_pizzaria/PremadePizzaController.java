package com.example.ru_pizzaria;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pizzaria.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

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
    private TextArea ta_premadeToppings;
    @FXML
    private ImageView iv_premadeImage;
    @FXML
    private Image chicagoBBQChicken;
    @FXML
    private Image chicagoMeatzza;
    @FXML
    private Image chicagoDeluxe;
    @FXML
    private Image nyBBQChicken;
    @FXML
    private Image nyMeatzza;
    @FXML
    private Image nyDeluxe;
    @FXML
    private TextField tf_crustStyle;
    @FXML
    private TextArea ta_errorLog;


    @FXML
    public void initialize() {
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

        if (pizzaOrder == null)
            pizzaOrder = new Order();
        chicagoBBQChicken = new Image(getClass().getResourceAsStream("/images/chicagoBBQChicken.jpg"));
        chicagoMeatzza = new Image(getClass().getResourceAsStream("/images/chicagoMeatzza.jpg"));
        chicagoDeluxe = new Image(getClass().getResourceAsStream("/images/chicagoDeluxe.jpg"));
        nyBBQChicken = new Image(getClass().getResourceAsStream("/images/nyBBQChicken.jpg"));
        nyMeatzza = new Image(getClass().getResourceAsStream("/images/nyMeatzza.jpg"));
        nyDeluxe = new Image(getClass().getResourceAsStream("/images/nyDeluxe.jpg"));


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
        if (rb_chicago.isSelected() && cb_pizzaType.getValue() != null) {
            currentPizza = premadeChicagoTypeSelected();
            tf_crustStyle.setText(currentPizza.getCrust().toString());
        }
        if (rb_ny.isSelected() && cb_pizzaType.getValue() != null) {
            currentPizza = premadeNYTypeSelected();
            tf_crustStyle.setText(currentPizza.getCrust().toString());
        }

        if (currentPizza != null) {
            if (pizzaSize.getSelectedToggle() != null) {
                String size = ((RadioButton) pizzaSize.getSelectedToggle()).getText();
                currentPizza.setSize(Size.valueOf(size.toUpperCase()));
                ta_premadeToppings.setText(currentPizza.getToppings().toString());
            }
        }
    }

    private void setPizzaSubtotal() {
        if (currentPizza != null) {
            if (pizzaSize.getSelectedToggle() != null) {
                DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
                double orderTotal = currentPizza.price();
                tf_pizzaPriceOut.setText(String.format("$%s", moneyFormat.format(orderTotal)));
            }
        }
    }

    private ObservableList<Topping> getPizzaToppings() {
        return null;
    }

    @FXML
    protected Pizza premadeChicagoTypeSelected() {
        PizzaFactory chicagoStyle = new ChicagoPizza();
        if (cb_pizzaType.getValue().equals("BBQ Chicken")) {
            iv_premadeImage.setImage(chicagoBBQChicken);
            return chicagoStyle.createBBQChicken();
        } else if (cb_pizzaType.getValue().equals("Deluxe")) {
            iv_premadeImage.setImage(chicagoDeluxe);
            return chicagoStyle.createDeluxe();
        } else if (cb_pizzaType.getValue().equals("Meatzza")) {
            iv_premadeImage.setImage(chicagoMeatzza);
            return chicagoStyle.createMeatzza();
        }
        return null;
    }

    @FXML
    protected Pizza premadeNYTypeSelected() {
        PizzaFactory nyStyle = new NYPizza();
        if (!cb_pizzaType.getSelectionModel().isEmpty()) {
            if (cb_pizzaType.getValue().equals("BBQ Chicken")) {
                iv_premadeImage.setImage(nyBBQChicken);
                return nyStyle.createBBQChicken();
            } else if (cb_pizzaType.getValue().equals("Deluxe")) {
                iv_premadeImage.setImage(nyDeluxe);
                return nyStyle.createDeluxe();
            } else if (cb_pizzaType.getValue().equals("Meatzza")) {
                iv_premadeImage.setImage(nyMeatzza);
                return nyStyle.createMeatzza();
            }
        }
        return null;
    }

    @FXML
    protected void onAddPizzaClick() throws IOException {
        if (currentPizza != null) {
            if (pizzaSize.getSelectedToggle() == null) { // maybe replace with currentPizza.getSize()?
                ta_errorLog.setText("Please select a pizza size.");
                return;
            }

            orderController.addPizza(currentPizza);
        } else {
            ta_errorLog.setText("Please select a pizza type.");
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