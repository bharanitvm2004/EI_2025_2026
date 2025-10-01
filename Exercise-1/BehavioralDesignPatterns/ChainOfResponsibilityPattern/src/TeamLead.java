// Concrete handler 1
public class TeamLead extends LeaveHandler {
    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 2) {
            System.out.println("TeamLead approved " + leaveDays + " days leave.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(leaveDays);
        }
    }
}
