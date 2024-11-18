package pizzaria;

/** Interface for creating Pizza Factories.
 * Pizza Factories are responsible for handling the creation of pizza objects.
 *
 * @author Elizabeth Tsang, Ron Chrysler Amistad
 */
public interface PizzaFactory {
    Pizza createDeluxe();
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();
}