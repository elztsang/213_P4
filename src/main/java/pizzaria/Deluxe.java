package pizzaria;

import java.util.ArrayList;
import java.util.Arrays;

public class Deluxe extends Pizza{
    private final static double SMALL = 16.99;
    private final static double MEDIUM = 18.99;
    private final static double LARGE = 20.99;

    private Crust crust;
    private ArrayList<Topping> toppings;

    public Deluxe() {

    }

    public Deluxe(Crust crust) {
        this.crust = crust;
        toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.GREENPEPPER,
                Topping.ONION,
                Topping.MUSHROOM));
    }

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

    @Override
    public String toString(){
        return String.format("[Deluxe, %s, %s, %s]",
                this.getSize().toString(),
                toppings.toString(),
                crust.toString());
    }
}
