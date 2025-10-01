
package controls;

import designpatterns.Observer;
import office.MeetingRoom;

/**
 * Implements the Observer interface to manage Air Conditioning based on room occupancy.
 */
public class ClimateControlSystem implements Observer {
    @Override
    public String update(MeetingRoom room) {
        if (room.isOccupied()) {
            room.setAcStatus("ON");
            return "AC turned ON for Room " + room.getRoomId() + ".";
        } else {
            room.setAcStatus("OFF");
            return "AC turned OFF for Room " + room.getRoomId() + ".";
        }
    }
}
