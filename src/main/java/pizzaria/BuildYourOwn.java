package pizzaria;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class BuildYourOwn extends Pizza{
    private final static double SMALL = 8.99;
    private final static double MEDIUM = 10.99;
    private final static double LARGE = 12.99;
    private final static double TOPPINGPRICE = 1.69;

    private Crust crust;
    private ArrayList<Topping> toppings;

    public BuildYourOwn() {

    }

    public BuildYourOwn(Crust crust) {
        this.crust = crust;
        super.setCrust(crust);
        toppings = new ArrayList<>();
    }

    @Override
    public double price() {
        double toppingPrice = 0.;

        for (int i = 0; i < toppings.size(); i++) {
            toppingPrice += TOPPINGPRICE;
        }

        if (this.getSize().equals(Size.SMALL))  {
            return SMALL + toppingPrice;
        } else if (this.getSize().equals(Size.MEDIUM)) {
            return MEDIUM + toppingPrice;
        } else if (this.getSize().equals(Size.LARGE)) {
            return LARGE + toppingPrice;
        } else {
            return -1; // no price
        }
    }

    public void setToppings(ArrayList<Topping> toppingsList) {
        if (toppings == null) {
            toppings = new ArrayList<>(); //allowed? causes error otherwise
        }
        toppings.addAll(toppingsList);
        super.setToppings(toppings);
    }

//    @Override
//    public String toString(){
//        DecimalFormat moneyFormat = new DecimalFormat("###,###.00");
//        return String.format("[BYOP, %s, %s, %s]",
//                this.getSize(),
//                toppings,
//                crust,
//                moneyFormat.format(price()));
//    }
}
