package office;

import designpatterns.Observer;
import controls.ClimateControlSystem;
import controls.LightingSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Meeting Room. Acts as the Subject in the Observer pattern.
 * Manages its own state, including occupancy, booking, and time tracking.
 */
public class MeetingRoom {
    private final int roomId;
    private final List<Observer> observers;

    // Room State
    private int maxCapacity = 0; // UPDATED: Default capacity is now 0 (unconfigured)
    private int currentOccupancy = 0;
    private boolean isOccupied = false; // True if currentOccupancy >= 2

    // Booking State
    private String bookingTime = null; // Format 'HH:MM'
    private int bookingDuration = 0; // in minutes
    private boolean isBooked = false;

    // Control System State (Managed by Observers)
    private String acStatus = "OFF";
    private String lightStatus = "OFF";

    // Time tracking for auto-release requirement (5 minutes = 300 seconds = 300,000 milliseconds)
    private long lastUnoccupiedTime = 0;
    private static final long AUTO_RELEASE_TIMEOUT_MS = 300_000;

    public MeetingRoom(int roomId) {
        this.roomId = roomId;
        this.observers = new ArrayList<>();
        
        // Attach default observers (Control Systems)
        this.attach(new ClimateControlSystem());
        this.attach(new LightingSystem());
    }

    // --- Observer Management (Public Methods) ---

    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    private List<String> notifyObservers() {
        List<String> messages = new ArrayList<>();
        for (Observer observer : observers) {
            messages.add(observer.update(this));
        }
        return messages;
    }

    // --- Core Functional Logic ---

    /**
     * Updates the occupancy count and status. Triggers observer notification if the occupied status changes.
     */
    public List<String> setOccupancy(int count) {
        if (count < 0) {
            return List.of("Occupancy count cannot be negative.");
        }

        boolean oldIsOccupied = this.isOccupied;
        this.currentOccupancy = count;

        // Rule 3: Occupancy detected only if at least two people enter (count >= 2)
        boolean newIsOccupied = count >= 2;

        List<String> messages = new ArrayList<>();

        if (oldIsOccupied != newIsOccupied) {
            this.isOccupied = newIsOccupied;

            // Update time tracking for Rule 4 (Unoccupied auto-release)
            if (!this.isOccupied) {
                this.lastUnoccupiedTime = System.currentTimeMillis();
                messages.add("Room " + roomId + " is now UNOCUPIED. Starting 5-minute auto-release timer.");
            } else {
                this.lastUnoccupiedTime = 0; // Reset timer if occupied
                messages.add("Room " + roomId + " is now OCCUPIED.");
            }

            // Rule 5: AC and lights control via Observer pattern
            messages.addAll(notifyObservers());
        }

        return messages;
    }

    /**
     * Checks if the room has been unoccupied for longer than the timeout and releases the booking. (Rule 4)
     * @return A status message if auto-release occurred, otherwise an empty string.
     */
    public String checkForAutoRelease() {
        if (isBooked && !isOccupied && lastUnoccupiedTime != 0) {
            long timePassed = System.currentTimeMillis() - lastUnoccupiedTime;

            if (timePassed > AUTO_RELEASE_TIMEOUT_MS) {
                // Perform the release
                isBooked = false;
                bookingTime = null;
                bookingDuration = 0;
                lastUnoccupiedTime = 0;
                
                // Ensure AC/Lights turn OFF (Rule 5)
                this.isOccupied = false; 
                notifyObservers(); 

                long minutesPassed = timePassed / 60000;

                return String.format(
                        "Room %d was unoccupied for ~%d minutes (> 5 min). Booking automatically released. AC and lights OFF.",
                        roomId, minutesPassed);
            }
        }
        return ""; // No auto-release action needed
    }

    /**
     * Returns a formatted string detailing the room's current state.
     */
    public String getStatusReport() {
        String bookingStatus = isBooked ?
                String.format("Booked from %s for %d mins", bookingTime, bookingDuration) :
                "Not booked";

        String capacityInfo = maxCapacity > 0 ? String.valueOf(maxCapacity) : "Not Configured";

        return String.format(
                "--- Room %d Status ---\n" +
                "  Max Capacity: %s\n" + // Uses the new 'Not Configured' string
                "  Occupancy: %d (%s)\n" +
                "  Booking: %s\n" +
                "  AC Status: %s\n" +
                "  Light Status: %s\n" +
                "----------------------------",
                roomId, capacityInfo, currentOccupancy, (isOccupied ? "Occupied" : "Unoccupied"),
                bookingStatus, acStatus, lightStatus);
    }

    // --- Getters and Setters ---

    public int getRoomId() { return roomId; }
    public boolean isOccupied() { return isOccupied; }
    public boolean isBooked() { return isBooked; }
    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }
    public void setBooking(String time, int duration) {
        this.isBooked = true;
        this.bookingTime = time;
        this.bookingDuration = duration;
    }
    public void clearBooking() {
        this.isBooked = false;
        this.bookingTime = null;
        this.bookingDuration = 0;
    }
    public void setAcStatus(String acStatus) { this.acStatus = acStatus; }
    public void setLightStatus(String lightStatus) { this.lightStatus = lightStatus; }
}
