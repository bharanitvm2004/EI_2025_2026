package commands;

import designpatterns.Command;
import office.SmartOffice;
import office.MeetingRoom;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Command to configure the number of meeting rooms.
 */
public class ConfigRoomsCommand implements Command {
    private final SmartOffice office;
    private final int count;

    public ConfigRoomsCommand(SmartOffice office, int count) {
        this.office = office;
        this.count = count;
    }

    @Override
    public List<String> execute() {
        if (count <= 0) {
            return List.of("Invalid room count. Must be a positive number.");
        }

        // Configure the rooms in the Singleton office instance
        office.configureRooms(count);
        
        // Use a utility method in SmartOffice to get room details for the report (assuming SmartOffice has getRooms utility)
        // Note: For simplicity, since the original SmartOffice didn't expose rooms publicly, we assume it does internally 
        // or we rely on the side effect of configureRooms. For this structure, we'll keep the reporting logic here, 
        // but need to rely on the side effect or an assumption about SmartOffice's API (e.g. getRooms is present, 
        // as implemented in the original monolithic command file).
        
        List<String> roomNames = office.getRooms().values().stream() // Assuming SmartOffice.getRooms() exists
                                    .map(r -> "Room " + r.getRoomId())
                                    .collect(Collectors.toList());
                                    
        String roomNamesStr = String.join(", ", roomNames);

        return List.of("Office configured with " + count + " meeting rooms: " + roomNamesStr + ".");
    }
}
