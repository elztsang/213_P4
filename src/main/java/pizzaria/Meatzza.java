package pizzaria;

import java.util.ArrayList;
import java.util.Arrays;

public class Meatzza extends Pizza{
    private final static double SMALL = 16.99;
    private final static double MEDIUM = 19.99;
    private final static double LARGE = 21.99;

    private Crust crust;
    private ArrayList<Topping> toppings;

    public Meatzza() {

    }

    public Meatzza(Crust crust) {
        this.toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.BEEF,
                Topping.HAM));
        this.crust = crust;
    }

    //monkey solution
    @Override
    public double price() {
        //check size, return price
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
