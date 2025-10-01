


// Abstract Decorator - wraps any Coffee object
public abstract class CoffeeDecorator implements Coffee {

    // Reference to a Coffee object
    protected Coffee coffee;

    // Constructor to initialize with a Coffee object
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    // Delegates description to the wrapped Coffee
    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    // Delegates cost to the wrapped Coffee
    @Override
    public double getCost() {
        return coffee.getCost();
    }
}
