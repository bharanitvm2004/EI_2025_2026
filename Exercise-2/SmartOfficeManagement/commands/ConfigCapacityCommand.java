package commands;

import designpatterns.Command;
import office.SmartOffice;
import office.MeetingRoom;

import java.util.List;

/**
 * Command to configure the maximum capacity for a specific room.
 */
public class ConfigCapacityCommand implements Command {
    private final SmartOffice office;
    private final int roomId;
    private final int capacity;

    public ConfigCapacityCommand(SmartOffice office, int roomId, int capacity) {
        this.office = office;
        this.roomId = roomId;
        this.capacity = capacity;
    }

    @Override
    public List<String> execute() {
        MeetingRoom room = office.getRoom(roomId);
        if (room == null) {
            return List.of("Room " + roomId + " does not exist.");
        }
        if (capacity <= 0) {
            return List.of("Invalid capacity (" + capacity + "). Please enter a valid positive number.");
        }
        
        room.setMaxCapacity(capacity);
        return List.of("Room " + roomId + " maximum capacity set to " + capacity + ".");
    }
}
