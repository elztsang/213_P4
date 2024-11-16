package pizzaria;

import java.text.DecimalFormat;
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

    //idk why i have to add this method to byop as well
    public void setToppings(ArrayList<Topping> toppingsList) {
        if (toppings == null) {
            toppings = new ArrayList<>(); //allowed? causes error otherwise
        }
        toppings.addAll(toppingsList);
    }

    public void setCrust(Crust crust){
        this.crust = crust;
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