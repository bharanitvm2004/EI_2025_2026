// Concrete Component - Cold Coffee
public class ColdCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Cold Coffee";
    }

    @Override
    public double getCost() {
        return 10.0;
    }
}
