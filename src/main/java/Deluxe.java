import java.util.ArrayList;
import java.util.Arrays;

public class Deluxe extends Pizza{
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
        return 0;
    }
}
