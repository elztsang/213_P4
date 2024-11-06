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
}
