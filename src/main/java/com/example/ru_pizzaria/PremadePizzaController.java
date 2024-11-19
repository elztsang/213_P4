package com.example.ru_pizzaria;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pizzaria.*;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Controller class that handles the ability to create and add Premade Pizzas to the current order.
 * @author Elizabeth Tsang, Ron Chrysler Amistad
 */
public class PremadePizzaController {
    private Order pizzaOrder;
    private Pizza currentPizza;

    private CreateOrderController orderController;

    @FXML
    private TextField tf_pizzaPriceOut;
    @FXML
    private ToggleGroup pizzaStyle;
    @FXML
    private RadioButton rb_chicago;
    @FXML
    private RadioButton rb_ny;

    @FXML
    private ToggleGroup pizzaSize;
    @FXML
    private RadioButton rb_smallPizza;
    @FXML
    private RadioButton rb_mediumPizza;
    @FXML
    private RadioButton rb_largePizza;

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


    /**
     * Handles initial loading of the Premade Pizza view.
     */
    @FXML
    public void initialize() {
        initPizzaStyleTG();
        initPizzaSizeTG();
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

    /**
     * Handles the Chicago pizza type selection event.
     * Updates the image to corresponding Chicago pizza and returns the matching Chicago pizza type.
     *
     * @return pizza
     */
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

    /**
     * Handles the NY pizza type selection event.
     * Updates the image to corresponding NY pizza and returns the matching NY pizza type.
     *
     * @return pizza
     */
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

    /**
     * Handles the add pizza button click event.
     * Adds the pizza to the current order's list of pizzas.
     * Validates that all fields have been selected/chosen before allowing pizza to be added.
     *
     * @throws IOException IOException
     */
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

    /**
     * Helper method that sets the initial subtotal of the pizza to $0.00.
     */
    private void setPizzaInitPrice() {
        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
        tf_pizzaPriceOut.setText(String.format("$%s", moneyFormat.format(0)));
    }

    /**
     * Helper method to initialize the pizza style toggle group.
     */
    private void initPizzaStyleTG() {
        pizzaStyle = new ToggleGroup();
        rb_chicago.setToggleGroup(pizzaStyle);
        rb_ny.setToggleGroup(pizzaStyle);
    }

    /**
     * Helper method to initialize the pizza size toggle group.
     */
    private void initPizzaSizeTG() {
        pizzaSize = new ToggleGroup();
        rb_smallPizza.setToggleGroup(pizzaSize);
        rb_mediumPizza.setToggleGroup(pizzaSize);
        rb_largePizza.setToggleGroup(pizzaSize);
    }

    /**
     * Helper method to initialize event listeners on the pizza style, size, and type values.
     * Updates the pizza and subtotal whenever these values are changed.
     */
    private void initPizzaDetailsListener() {
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

    /**
     * Helper method to update the pizza object with the selected options.
     */
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

    /**
     * Helper method to help update the pizza subtotal whenever called.
     * First validates that a pizza exists and that a size is selected before allowing the price to update.
     */
    private void setPizzaSubtotal() {
        if (currentPizza != null) {
            if (pizzaSize.getSelectedToggle() != null) {
                DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
                double orderTotal = currentPizza.price();
                tf_pizzaPriceOut.setText(String.format("$%s", moneyFormat.format(orderTotal)));
            }
        }
    }

    public void setOrderController(CreateOrderController controller) {
        orderController = controller;
    }
}