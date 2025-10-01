package designpatterns;

import office.MeetingRoom;

/**
 * Observer interface: For control systems that react to changes in a room's state.
 */
public interface Observer {
    /**
     * Called by the Subject (MeetingRoom) to notify the Observer of a state change.
     * @param room The MeetingRoom instance that changed.
     * @return A status message indicating the action taken by the observer.
     */
    String update(MeetingRoom room);
}
