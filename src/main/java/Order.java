import java.util.ArrayList;

public class Order {
    private int number; //order number
    private ArrayList<Pizza> pizzas; //can use List<E> instead of ArrayList<E>

    /**
     * Order default constructor.
     */
    public Order () {

    }

    /**
     * Return order number.
     */
    public int getOrderNumber() {
        return number;
    }

    //todo: figure out how we want to generate this
    public static int generateOrderNumber() {
        return 0;
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

    public void exportOrder() {
        //export order details into text file
    }

    public double getTotal() {
        double total = 0;

        for (Pizza pizza : pizzas) {
            total += pizza.price();
        }

        return total;
    }

    public double getOrderTotal() {
        return getTotal() *  1.06625;
    }

    public int getNumberPizzas(){
        return pizzas.size();
    }
}