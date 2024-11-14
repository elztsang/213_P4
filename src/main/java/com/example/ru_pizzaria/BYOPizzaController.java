package com.example.ru_pizzaria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import pizzaria.*;

import java.util.ArrayList;

public class BYOPizzaController {

    @FXML
    private ToggleGroup pizzaStyle;
    @FXML
    private ToggleGroup pizzaSize;
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
    public void initialize() {
        initPizzaStyleTG();
        initPizzaSizeTG();
        initToppingsLV();
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
    protected void onAddPizzaClick() {
        ArrayList<Topping> selectedToppings;
        selectedToppings = getSelectedToppings();

        if (selectedToppings == null) {
            //print error message like "too many toppings selected"
            System.out.println("Please select 7 toppings at most."); // move to area visible to user
            return;
        }
        if (rb_chicago.isSelected()) {
            //create chicago pizza with specified toppings + size
            PizzaFactory chicagoStyle = new ChicagoPizza();
            Pizza pizza = chicagoStyle.createBuildYourOwn();
            if (pizza != null) {
                if (pizzaSize.getSelectedToggle() == null) {
                    //print error message like "please select a size"
                    System.out.println("Please select size"); //move this to a visible area for user
                    return;
                }

                pizza.setToppings(selectedToppings);
                String size = ((RadioButton) pizzaSize.getSelectedToggle()).getText();
                pizza.setSize(Size.valueOf(size.toUpperCase())); //get selection
                CreateOrderController.addPizza(pizza);
            } else {
                System.out.println("Pizza null"); //change error message
            }
        }

        if (rb_ny.isSelected()) {
            //create ny pizza with specified toppings + size
            PizzaFactory nyStyle = new NYPizza();
            Pizza pizza = nyStyle.createBuildYourOwn();
            if (pizza != null) {
                if (pizzaSize.getSelectedToggle() == null) {
                    //print error message like "please select a size"
                    System.out.println("Please select size"); //move this to a visible area for user
                    return;
                }

                pizza.setToppings(selectedToppings);
                String size = ((RadioButton) pizzaSize.getSelectedToggle()).getText();
                pizza.setSize(Size.valueOf(size.toUpperCase())); //get selection
                CreateOrderController.addPizza(pizza);
            } else {
                System.out.println("Pizza null"); //change error message
            }
        }
    }
}
