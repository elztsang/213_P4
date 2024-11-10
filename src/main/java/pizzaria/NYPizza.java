package pizzaria;

public class NYPizza implements PizzaFactory {
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.BROOKLYN);
    }

    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.HANDTOSSED);
    }

    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.THIN);
    }

    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.HANDTOSSED);
    }
}