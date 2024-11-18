package pizzaria;

import java.util.ArrayList;
import java.util.Arrays;

public class BBQChicken extends Pizza{
    private final static double SMALL = 14.99;
    private final static double MEDIUM = 16.99;
    private final static double LARGE = 19.99;

    private Crust crust;
    private ArrayList<Topping> toppings;

    public BBQChicken(){

    }

    public BBQChicken(Crust crust){
        super.setCrust(crust);
        this.crust = crust;
        toppings = new ArrayList<>(Arrays.asList(Topping.BBQCHICKEN,
                Topping.GREENPEPPER,
                Topping.PROVOLONE,
                Topping.CHEDDAR));
        super.setToppings(toppings);
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
}
