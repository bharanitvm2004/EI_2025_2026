public class Main {
    public static void main(String[] args) {
        // Create notifier with product name
        iPhoneLaunchNotifier notifier = new iPhoneLaunchNotifier("iPhone 15");

        // Create customers
        AmazonUser user1 = new AmazonUser("Alice");
        AmazonUser user2 = new AmazonUser("Bob");
        AmazonUser user3 = new AmazonUser("Charlie");

        // Subscribe users
        notifier.subscribe(user1);
        notifier.subscribe(user2);
        notifier.subscribe(user3);

        System.out.println("\n--- Product Launch Notification ---");
        // Notify all customers
        notifier.notifyCustomers();

        // Unsubscribe one user
        notifier.unsubscribe(user2);

        System.out.println("\n--- Another Product Notification ---");
        notifier.notifyCustomers();
    }
}
