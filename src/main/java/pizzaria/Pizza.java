package pizzaria;

import java.util.ArrayList;

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
        toppings.addAll(toppingsList);
    }
}