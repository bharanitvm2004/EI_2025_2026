

// Concrete Component 1 - Simple Coffee
public class SimpleCoffee implements Coffee {

    // Returns description of Simple Coffee
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    // Returns cost of Simple Coffee
    @Override
    public double getCost() {
        return 5.0;
    }
}
