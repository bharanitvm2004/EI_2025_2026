import java.util.*;
// Client
public class CarDealerClient {
    public List<Car> getCarList() {
        List<Car> carList = new ArrayList<>();

        Car indianCar1 = new IndianCar("Punch", "Tata", 100000);
        ForeignCar foreignCar = new ForeignCar("Spectre", "Rolls Royce", 1009900);

        carList.add(indianCar1);//provide the output

        // carList.add(foreignCar); 
        // This will give compilation error (ForeignCar does not implement Car)
        

        // Correct way: use Adapter
        carList.add(new ForeignCarAdapter(foreignCar));

        return carList;
    }
}
