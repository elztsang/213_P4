package pizzaria;

import java.util.ArrayList;
import java.util.Arrays;

/** Meatzza is a subclass of the Pizza class.
 * Defines a pizza type with Sausage, Pepperoni, Beef, and Ham as toppings.
 *
 * @author Elizabeth Tsang, Ron Chrysler Amistad
 */
public class Meatzza extends Pizza{
    private final static double SMALL = 17.99;
    private final static double MEDIUM = 19.99;
    private final static double LARGE = 21.99;

    private Crust crust;
    private ArrayList<Topping> toppings;

    public Meatzza() {
        toppings = new ArrayList<>();
    }

    public Meatzza(Crust crust) {
        super.setCrust(crust);
        this.toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.BEEF,
                Topping.HAM));
        super.setToppings(toppings);
        this.crust = crust;
    }

    @Override
    public double price() {
        if (this.getSize().equals(Size.SMALL))  {
            return SMALL;
        } else if (this.getSize().equals(Size.MEDIUM)) {
            return MEDIUM;
        } else if (this.getSize().equals(Size.LARGE)) {
            return LARGE;
        } else {
            return -1; // no price
        }
    }
}
