import java.util.*;

// Concrete Subject
public class iPhoneLaunchNotifier implements ProductLaunchNotifier {
    private List<Customer> customers;
    private String productName;

    public iPhoneLaunchNotifier(String productName) {
        this.productName = productName;
        customers = new ArrayList<>();
    }

    @Override
    public void subscribe(Customer customer) {
        customers.add(customer);
        System.out.println(customer + " subscribed successfully!");
    }

    @Override
    public void unsubscribe(Customer customer) {
        customers.remove(customer);
        System.out.println(customer + " unsubscribed successfully!");
    }

    @Override
    public void notifyCustomers() {
        for (Customer customer : customers) {
            customer.notify("The new " + productName + " is now available on Amazon!");
        }
    }
}
