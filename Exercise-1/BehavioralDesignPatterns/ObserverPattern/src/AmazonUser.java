// Concrete Observer
public class AmazonUser implements Customer {
    private String name;

    public AmazonUser(String name) {
        this.name = name;
    }

    @Override
    public void notify(String message) {
        System.out.println("Hi " + name + ", " + message);
    }

    @Override
    public String toString() {
        return name;
    }
}
