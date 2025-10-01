// Concrete handler 2
public class ProjectManager extends LeaveHandler {
    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 5) {
            System.out.println("ProjectManager approved " + leaveDays + " days leave.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(leaveDays);
        }
    }
}
