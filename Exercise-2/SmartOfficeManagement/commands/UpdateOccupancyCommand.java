package commands;

import designpatterns.Command;
import office.SmartOffice;
import office.MeetingRoom;

import java.util.List;

/**
 * Command to update the physical occupancy of a room.
 */
public class UpdateOccupancyCommand implements Command {
    private final SmartOffice office;
    private final int roomId;
    private final int count;

    public UpdateOccupancyCommand(SmartOffice office, int roomId, int count) {
        this.office = office;
        this.roomId = roomId;
        this.count = count;
    }

    @Override
    public List<String> execute() {
        MeetingRoom room = office.getRoom(roomId);
        
        if (room == null) {
            return List.of("Room " + roomId + " does not exist.");
        }

        // 1. Capacity Configuration Check (Aligned with mandatory configuration requirement)
        if (room.getMaxCapacity() == 0) {
            return List.of("Cannot add occupants to Room " + roomId + ". Maximum capacity must be configured first using 'Config room max capacity'.");
        }
        
        // 2. Negative Occupancy Check (Moved here from MeetingRoom for Command-level validation)
        if (count < 0) {
            return List.of("Occupancy count cannot be negative.");
        }

        // 3. Max Capacity Check
        if (count > room.getMaxCapacity()) {
            return List.of("Occupancy count (" + count + ") exceeds Room " + roomId + " capacity (" + room.getMaxCapacity() + ").");
        }
        
        // Rule 3: Must have at least 2 occupants to be marked as occupied
        if (count > 0 && count < 2) {
            room.setOccupancy(count); // Update count but won't trigger observer (isOccupied remains false)
            return List.of("Room " + roomId + " occupancy is " + count + ". Insufficient to mark as occupied (needs 2). AC and lights remain OFF.");
        }
        
        // Room's setOccupancy handles the Observer notification logic
        List<String> messages = room.setOccupancy(count);
        
        // Add a descriptive message based on the room's resulting state
        if (room.isOccupied()) {
            messages.add(0, "Room " + roomId + " is now occupied by " + count + " persons.");
        } else if (count == 0) {
            messages.add(0, "Room " + roomId + " is now unoccupied (0 persons).");
        }

        return messages;
    }
}
