package pizzaria;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order {
    private final double NJSALESTAX = 0.06625;
    private int number; //order number
    private ArrayList<Pizza> pizzas; //can use List<E> instead of ArrayList<E>

    /**
     * pizzaria.Order default constructor.
     */
    public Order () {
        pizzas = new ArrayList<>();
        number = -1;
    }

    /**
     * Return order number.
     */
    public int getOrderNumber() {
        return number;
    }

    public int setOrderNumber(int number) {
        return this.number = number;
    }

    //maybe unnecessary? not sure
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    //todo: figure out how we want to do this
    // few options depending on how we implement the gui
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    public void removeAllPizzas() {
        pizzas.clear();
    }

    public double getTotal() {
        double total = 0;

        for (Pizza pizza : pizzas) {
            total += pizza.price();
        }

        return total;
    }

    public double getSalesTax() {
        return getTotal() * NJSALESTAX;
    }

    public double getOrderTotal() {
        return getTotal() + getSalesTax();
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    @Override
    public String toString() {
        DecimalFormat moneyFormat = new DecimalFormat("###,##0.00");
        return String.format("[#%s] [%s] [%s]",
                number,
                String.format("$%s", moneyFormat.format(getOrderTotal())),
                pizzas);
    }
}