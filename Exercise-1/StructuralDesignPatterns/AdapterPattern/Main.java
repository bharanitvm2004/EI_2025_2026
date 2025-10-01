import src.*;

import java.util.*;
// Main class
public class Main {
    public static void main(String[] args) {
        CarDealerClient client = new CarDealerClient();
        List<Car> cars = client.getCarList();

        for (Car car : cars) {
            System.out.println(car.getName() + " -> " + car.getBrand() + " -> " + car.getPrice());
        }
    }
}