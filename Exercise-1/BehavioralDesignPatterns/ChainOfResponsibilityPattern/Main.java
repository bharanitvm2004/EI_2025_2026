public class Main {
    public static void main(String[] args) {
        LeaveHandler teamLead = new TeamLead();
        LeaveHandler projectManager = new ProjectManager();
        LeaveHandler hr = new HR();

        // Setup the chain
        teamLead.setNextHandler(projectManager);
        projectManager.setNextHandler(hr);

        System.out.println("--- Leave Requests ---");
        teamLead.handleRequest(1);  // TeamLead approves
        teamLead.handleRequest(3);  // ProjectManager approves
        teamLead.handleRequest(7);  // HR approves
    }
}
 
