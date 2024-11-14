package pizzaria;

import java.util.ArrayList;
import java.util.Arrays;

public class Meatzza extends Pizza{
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
        return 0;
    }
}
