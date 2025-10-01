
// Concrete handler 3
public class HR extends LeaveHandler {
    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays > 5) {
            System.out.println("HR approved " + leaveDays + " days leave.");
        }
    }
}
