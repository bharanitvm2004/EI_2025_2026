

// Concrete Decorator - adds Milk to any Coffee
public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    // Adds "Milk" to the description
    @Override
    public String getDescription() {
        return coffee.getDescription() + " + Milk";
    }

    // Adds 1.5 to the cost
    @Override
    public double getCost() {
        return coffee.getCost() + 1.5;
    }
}
