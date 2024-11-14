package com.example.ru_pizzaria;

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
    private RadioButton rb_chicago;
    @FXML
    private RadioButton rb_ny;
    @FXML
    private Button b_addpizza;
    @FXML
    private ListView<Topping> lv_toppingslist;

    @FXML
    public void initialize() {
        pizzaStyle = new ToggleGroup();
        rb_chicago.setToggleGroup(pizzaStyle);
        rb_ny.setToggleGroup(pizzaStyle);
        lv_toppingslist = new ListView<>();
        lv_toppingslist.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //javadoc says to do this if we want multiple selections - ron
    }

    private ArrayList<Topping> getSelectedToppings() {
        ObservableList<Topping> selectedToppings = lv_toppingslist.getSelectionModel().getSelectedItems();
        ArrayList<Topping> toppings = new ArrayList<>(selectedToppings);
        return toppings;
    }

    /*
    some considerations to make later
    - ensure there are >=7 toppings selected
    - ensure size option is selected
    - display 13 toppings
     */
    @FXML
    protected void onAddPizzaClick() {
        ArrayList<Topping> selectedToppings = getSelectedToppings();

        if (rb_chicago.isSelected()) {
            //create chicago pizza with specified toppings + size
            PizzaFactory chicagoStyle = new ChicagoPizza();
            Pizza pizza = chicagoStyle.createBuildYourOwn();
            String size = ((RadioButton) pizzaSize.getSelectedToggle()).getText();
            pizza.setSize(Size.valueOf(size.toUpperCase()));
            pizza.setToppings(selectedToppings);
            CreateOrderController.addPizza(pizza);
        }

        if (rb_ny.isSelected()) {
            //create ny pizza with specified toppings + size
            PizzaFactory nyStyle = new NYPizza();
            Pizza pizza = nyStyle.createBuildYourOwn();
            String size = ((RadioButton) pizzaSize.getSelectedToggle()).getText();
            pizza.setSize(Size.valueOf(size.toUpperCase()));
            pizza.setToppings(selectedToppings);
            CreateOrderController.addPizza(pizza);
        }
    }
}
