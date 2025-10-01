// Adapter - makes ForeignCar compatible with Car interface
public class ForeignCarAdapter implements Car {
    private ForeignCar foreignCar;

    public ForeignCarAdapter(ForeignCar foreignCar) {
        this.foreignCar = foreignCar;
    }

    @Override
    public String getName() {
        return foreignCar.getCarName();
    }

    @Override
    public String getBrand() {
        return foreignCar.getCarBrand();
    }

    @Override
    public int getPrice() {
        return foreignCar.getBrandPrice();
    }
}