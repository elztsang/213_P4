package pizzaria;

import java.text.DecimalFormat;
import java.util.ArrayList;

/** Pizza is an abstract class that stores information about toppings, crust, and size.
 * Also defines a price method, which determines the price of the pizza based on size and type.
 *
 * @author Elizabeth Tsang, Ron Chrysler Amistad
 */
public abstract class Pizza {
    private ArrayList<Topping> toppings; //pizzaria.Topping is a Enum class
    private Crust crust; //pizzaria.Crust is a Enum class
    private Size size; //pizzaria.Size is a Enum class
    public abstract double price();

    public void setSize(Size size){
        this.size = size;
    }

    public Size getSize() {
        return this.size;
    }

    public void setToppings(ArrayList<Topping> toppingsList) {
        if (toppings == null) {
            toppings = new ArrayList<>();
        }
        toppings.addAll(toppingsList);
    }

    public void setCrust(Crust crust){
        this.crust = crust;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public Crust getCrust() {
        return crust;
    }

    @Override
    public String toString(){
        DecimalFormat moneyFormat = new DecimalFormat("###,###.00");
        return String.format("%s, %s, (%s - %s) %s $%s",
                this.getClass().getSimpleName(),
                size,
                crust.name(),
                crust.getCrustType(),
                toppings,
                moneyFormat.format(price()));
    }
}