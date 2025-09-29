import src.*;

// Main class
public class Main {
    public static void main(String args[]) {
        
        // 1. Creating Phone object WITHOUT Builder (direct constructor usage)
        Phone normalPhone = new Phone("iOS", "A16 Bionic", 48, 4000);
        System.out.println("Phone created directly without Builder:");
        System.out.println(normalPhone);  
        // Output: Phone [os=iOS, processor=A16 Bionic, camera=48,battery=4000]
        
        //2. Creating Phone object WITH Builder (step by step)
        Phone builtPhone = new PhoneBuilder()
                                
                                .setOs("Android")// Setting OS
                                .setProcessor("Snapdragon") // Setting Processor
                                .setCamera(64)// Setting Camera
                                .setBattery(5000)// Setting Battery
                                .getPhone();// Finally build Phone

        System.out.println("Phone created using Builder:");
        System.out.println(builtPhone);  
        // Output: Phone [os=Android, processor=Snapdragon, camera=64, battery=5000]

        Phone builtPhone2 = new PhoneBuilder()
                                .setCamera(64)// Setting Camera
                                .setOs("Android")// Setting OS
                                .setProcessor("Snapdragon") // Setting Processor
                                .setBattery(5000)// Setting Battery
                                .getPhone();// Finally build Phone

        System.out.println("Phone created using Builder:");
        System.out.println(builtPhone2);
        // Output: Phone [os=Android, processor=Snapdragon, camera=64, battery=5000]  
    }
}

