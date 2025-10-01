package commands;

import designpatterns.Command;
import office.SmartOffice;
import office.MeetingRoom;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Command to book a specific room.
 */
class BookRoomCommand implements Command {
    private final SmartOffice office;
    private final int roomId;
    private final String startTime;
    private final int duration;
    
    // Simple regex for HH:MM format validation
    private static final Pattern TIME_PATTERN = Pattern.compile("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$");

    public BookRoomCommand(SmartOffice office, int roomId, String startTime, int duration) {
        this.office = office;
        this.roomId = roomId;
        this.startTime = startTime;
        this.duration = duration;
    }

    @Override
    public List<String> execute() {
        MeetingRoom room = office.getRoom(roomId);
        
        if (room == null) {
            return List.of("Room " + roomId + " does not exist.");
        }
        
        if (room.isBooked()) {
            return List.of("Room " + roomId + " is already booked. Cannot book.");
        }
        
        if (duration <= 0) {
            return List.of("Invalid duration (" + duration + "). Must be positive.");
        }
        
        if (!TIME_PATTERN.matcher(startTime).matches()) {
             return List.of("Invalid time format (" + startTime + "). Please use HH:MM (e.g., 09:00).");
        }
            
        room.setBooking(startTime, duration);
        
        return List.of("Room " + roomId + " booked from " + startTime + " for " + duration + " minutes.");
    }
}

/**
 * Command to cancel a booking for a specific room.
 */
class CancelBookingCommand implements Command {
    private final SmartOffice office;
    private final int roomId;

    public CancelBookingCommand(SmartOffice office, int roomId) {
        this.office = office;
        this.roomId = roomId;
    }

    @Override
    public List<String> execute() {
        MeetingRoom room = office.getRoom(roomId);
        
        if (room == null) {
            return List.of("Room " + roomId + " does not exist.");
        }

        if (!room.isBooked()) {
            return List.of("Room " + roomId + " is not booked. Cannot cancel booking.");
        }
            
        room.clearBooking();
        
        return List.of("Booking for Room " + roomId + " cancelled successfully.");
    }
}

/**
 * Command to update the physical occupancy of a room.
 */
class UpdateOccupancyCommand implements Command {
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

/**
 * Command to display the current status of a room.
 */
class RoomStatusCommand implements Command {
    private final SmartOffice office;
    private final int roomId;

    public RoomStatusCommand(SmartOffice office, int roomId) {
        this.office = office;
        this.roomId = roomId;
    }

    @Override
    public List<String> execute() {
        MeetingRoom room = office.getRoom(roomId);
        
        if (room == null) {
            return List.of("Room " + roomId + " does not exist.");
        }

        // Check for and apply auto-release immediately before reporting status
        String releaseMsg = room.checkForAutoRelease();
        
        List<String> messages = new java.util.ArrayList<>();
        if (!releaseMsg.isEmpty()) {
            messages.add(releaseMsg);
        }
        
        messages.add(room.getStatusReport());
        
        return messages;
    }
}
