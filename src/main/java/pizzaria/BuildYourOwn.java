package pizzaria;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza{
    private Crust crust;
    private ArrayList<Topping> toppings;

    public BuildYourOwn() {

    }

    public BuildYourOwn(Crust crust) {
        this.crust = crust;
        toppings = new ArrayList<>();
    }

    @Override
    public double price() {
        return 0;
    }

    public void addToppings(){  //TODO: figure out format of multi-select group (is it an array?)

    }
}
