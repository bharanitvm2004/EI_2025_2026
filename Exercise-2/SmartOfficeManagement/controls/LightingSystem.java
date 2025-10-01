package controls;

import designpatterns.Observer;
import office.MeetingRoom;

/**
 * Implements the Observer interface to manage Lighting based on room occupancy.
 */
public class LightingSystem implements Observer {
    @Override
    public String update(MeetingRoom room) {
        if (room.isOccupied()) {
            room.setLightStatus("ON");
            return "Lights turned ON for Room " + room.getRoomId() + ".";
        } else {
            room.setLightStatus("OFF");
            return "Lights turned OFF for Room " + room.getRoomId() + ".";
        }
    }
}
