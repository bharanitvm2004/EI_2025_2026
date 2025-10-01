package commands;

import designpatterns.Command;
import office.SmartOffice;
import office.MeetingRoom;

import java.util.List;

/**
 * Command to display the current status of a room.
 */
public class RoomStatusCommand implements Command {
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
