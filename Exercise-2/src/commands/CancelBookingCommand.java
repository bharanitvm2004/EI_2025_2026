package commands;

import designpatterns.Command;
import office.SmartOffice;
import office.MeetingRoom;

import java.util.List;

/**
 * Command to cancel a booking for a specific room.
 */
public class CancelBookingCommand implements Command {
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
