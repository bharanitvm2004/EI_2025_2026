

// Concrete Decorator - adds Sugar to any Coffee
public class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    // Adds "Sugar" to the description
    @Override
    public String getDescription() {
        return coffee.getDescription() + " + Sugar";
    }

    // Adds 0.5 to the cost
    @Override
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}
