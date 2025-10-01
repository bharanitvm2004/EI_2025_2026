


package commands;

import designpatterns.Command;
import office.SmartOffice;
import office.MeetingRoom;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Command to book a specific room.
 */
public class BookRoomCommand implements Command {
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
