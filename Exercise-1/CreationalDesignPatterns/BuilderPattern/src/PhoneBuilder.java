package src;
// Builder class - helps us build Phone object step by step
public class PhoneBuilder {
    private String os;
    private String processor;
    private int camera;
    private int battery;

    // Setter methods that return builder object for method chaining
    public PhoneBuilder setOs(String os) {
        this.os = os;
        return this;
    }

    public PhoneBuilder setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public PhoneBuilder setCamera(int camera) {
        this.camera = camera;
        return this;
    }

    public PhoneBuilder setBattery(int battery) {
        this.battery = battery;
        return this;
    }

    // Finally build the Phone object
    public Phone getPhone() {
        return new Phone(os, processor, camera, battery);
    }
}
