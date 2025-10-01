

// Client class to demonstrate the Decorator Pattern
public class Main {
    public static void main(String[] args) {

        // Simple Coffee
        Coffee simple = new SimpleCoffee();
        System.out.println(simple.getDescription() + " : $" + simple.getCost());

        // Simple Coffee + Milk
        Coffee simpleMilk = new MilkDecorator(new SimpleCoffee());
        System.out.println(simpleMilk.getDescription() + " : $" + simpleMilk.getCost());

        // Simple Coffee + Sugar
        Coffee simpleSugar = new SugarDecorator(new SimpleCoffee());
        System.out.println(simpleSugar.getDescription() + " : $" + simpleSugar.getCost());

        // Simple Coffee + Milk + Sugar
        Coffee simpleMilkSugar = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println(simpleMilkSugar.getDescription() + " : $" + simpleMilkSugar.getCost());

        // Cold Coffee
        Coffee cold = new ColdCoffee();
        System.out.println(cold.getDescription() + " : $" + cold.getCost());

        // Cold Coffee + Milk
        Coffee coldMilk = new MilkDecorator(new ColdCoffee());
        System.out.println(coldMilk.getDescription() + " : $" + coldMilk.getCost());

        // Cold Coffee + Milk + Sugar
        Coffee coldMilkSugar = new SugarDecorator(new MilkDecorator(new ColdCoffee()));
        System.out.println(coldMilkSugar.getDescription() + " : $" + coldMilkSugar.getCost());
    }
}
