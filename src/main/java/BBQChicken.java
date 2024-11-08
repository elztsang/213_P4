import java.util.ArrayList;
import java.util.Arrays;

public class BBQChicken extends Pizza{
    private Crust crust;
    private ArrayList<Topping> toppings;

    public BBQChicken(){

    }

    public BBQChicken(Crust crust){
        this.crust = crust;
        toppings = new ArrayList<>(Arrays.asList(Topping.BBQCHICKEN,
                Topping.GREENPEPPER,
                Topping.PROVOLONE,
                Topping.CHEDDAR));
    }

    @Override
    public double price() {
        return 0;
    }
}
