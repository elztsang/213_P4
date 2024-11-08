import java.util.ArrayList;

public abstract class Pizza {
    private ArrayList<Topping> toppings; //Topping is a Enum class
    private Crust crust; //Crust is a Enum class
    private Size size; //Size is a Enum class
    public abstract double price();

    public void setSize(Size size){
        this.size = size;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }
}