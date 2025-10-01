package src;
// Phone class - represents the final object we want to build
public class Phone {
    private String os;
    private String processor;
    private int camera;
    private int battery;

    // Constructor
    public Phone(String os, String processor, int camera, int battery) {
        this.os = os;
        this.processor = processor;
        this.camera = camera;
        this.battery = battery;
    }

    @Override
    public String toString() {
        return "Phone [os=" + os + ", processor=" + processor + ", camera=" + camera + ", battery=" + battery + "]";
    }
}