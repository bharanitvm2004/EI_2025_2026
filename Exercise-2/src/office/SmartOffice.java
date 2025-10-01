package office;

import designpatterns.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SmartOffice: Manages the global state and configuration of the facility.
 * Implements the Singleton Pattern.
 */
public class SmartOffice {
    private static SmartOffice instance;
    private final Map<Integer, MeetingRoom> rooms;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private SmartOffice() {
        rooms = new HashMap<>();
    }

    /**
     * Singleton accessor method.
     */
    public static SmartOffice getInstance() {
        if (instance == null) {
            instance = new SmartOffice();
        }
        return instance;
    }

    /**
     * Executes the Command. Acts as the Invoker in the Command pattern.
     */
    public List<String> executeCommand(Command command) {
        return command.execute();
    }

    /**
     * Utility method to safely get a room instance by ID.
     */
    public MeetingRoom getRoom(int roomId) {
        return rooms.get(roomId);
    }
    
    /**
     * Provides access to the full map of rooms. Required for configuration reporting.
     */
    public Map<Integer, MeetingRoom> getRooms() {
        return rooms;
    }

    /**
     * Configures the facility by setting the number of rooms.
     */
    public void configureRooms(int count) {
        rooms.clear();
        for (int i = 1; i <= count; i++) {
            rooms.put(i, new MeetingRoom(i));
        }
    }

    /**
     * Iterates over all rooms to check for the 5-minute auto-release condition.
     */
    public List<String> checkAllRoomsForRelease() {
        List<String> messages = new ArrayList<>();
        for (MeetingRoom room : rooms.values()) {
            String releaseMessage = room.checkForAutoRelease();
            if (!releaseMessage.isEmpty()) {
                messages.add(releaseMessage);
            }
        }
        return messages;
    }
}
