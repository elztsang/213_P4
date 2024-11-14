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

    //not sure how to populate tableview
//    public Crust getCrust() {
//        return this.crust;
//    }
//
//    public double getPrice() {
//        return price();
//    }
//
//    public String getStyle() {
//        return "er";
//    }
//

    //idk why i have to add this method to byop as well
    public void setToppings(ArrayList<Topping> toppingsList) {
        if (toppings == null) {
            toppings = new ArrayList<>(); //allowed? causes error otherwise
        }
        toppings.addAll(toppingsList);
    }
}