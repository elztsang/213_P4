package pizzaria;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BuildYourOwnTest {
    Pizza pizza;
    ArrayList<Topping> testToppings;

    @Before
    public void setUp() throws Exception {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        pizza = pizzaFactory.createBuildYourOwn();
        testToppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.GREENPEPPER,
                Topping.BEEF,
                Topping.CHEDDAR,
                Topping.ONION,
                Topping.MUSHROOM,
                Topping.PROVOLONE));
    }

    @Test
    public void testNumberOfToppings() {
        // >7 toppings
        pizza.setSize(Size.valueOf("MEDIUM"));
        pizza.setToppings(testToppings);
        assertEquals(24.51, pizza.price(), 0.001); //floating point error

        // 1 topping
        ArrayList<Topping> testToppings2 = new ArrayList<>(List.of(Topping.SAUSAGE));
        pizza.setToppings(testToppings2);
        assertEquals(12.68, pizza.price(), 0);

    }

    @Test
    public void testNoSize() {
        pizza.setToppings(testToppings);
        assertEquals(-1, pizza.price(), 0);
    }

    @Test
    public void testSizes() {
        pizza.setSize(Size.valueOf("SMALL"));
        assertEquals(8.99, pizza.price(), 0);
        pizza.setSize(Size.valueOf("MEDIUM"));
        assertEquals(10.99, pizza.price(), 0);
        pizza.setSize(Size.valueOf("LARGE"));
        assertEquals(12.99, pizza.price(), 0);
    }
    //test cases

    // test small, med, large - 3 toppings each
    // test with no toppings, test with 7 toppings, and one with 8?
}