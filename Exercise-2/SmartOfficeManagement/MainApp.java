

import designpatterns.Command;
import office.SmartOffice;
import commands.ConfigRoomsCommand;
import commands.ConfigCapacityCommand;
import commands.BookRoomCommand;
import commands.CancelBookingCommand;
import commands.UpdateOccupancyCommand;
import commands.RoomStatusCommand;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MainApp: Console application entry point. Acts as the Invoker and Client.
 * Handles user input parsing and the main simulation loop.
 */
public class MainApp {

    private static final SmartOffice office = SmartOffice.getInstance();
    private static final Pattern CONFIG_ROOMS_PATTERN = Pattern.compile("config room count (\\d+)");
    
    // FIX: Updated to accept an optional negative sign for capacity (-?\\d+)
    private static final Pattern CONFIG_CAPACITY_PATTERN = Pattern.compile("config room max capacity (\\d+) (-?\\d+)");
    
    private static final Pattern BLOCK_ROOM_PATTERN = Pattern.compile("block room (\\d+) ([0-2][0-9]:[0-5][0-9]) (\\d+)");
    private static final Pattern CANCEL_ROOM_PATTERN = Pattern.compile("cancel room (\\d+)");
    
    // FIX: Updated to accept an optional negative sign for count (-?\\d+)
    private static final Pattern ADD_OCCUPANT_PATTERN = Pattern.compile("add occupant (\\d+) (-?\\d+)");
    
    private static final Pattern ROOM_STATUS_PATTERN = Pattern.compile("room status (\\d+)");

    public static void main(String[] args) {
        
        System.out.println("--- Smart Office Facility Management System ---");
        System.out.println("Design Patterns Used: Singleton, Observer, Command");
        printHelp();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // 4. Auto-Release Check (Simulation of time-based background task)
                List<String> releaseMessages = office.checkAllRoomsForRelease();
                for (String msg : releaseMessages) {
                    System.out.println("|AUTOMATED| " + msg);
                }

                System.out.print("\nEnter command: ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting Smart Office System. Goodbye!");
                    break;
                }
                
                if (input.isEmpty()) continue;

                Command command = parseCommand(input);
                
                if (command != null) {
                    List<String> results = office.executeCommand(command);
                    for (String result : results) {
                        System.out.println(result);
                    }
                } else {
                    System.out.println("ERROR: Unknown command or invalid arguments. Type 'help' for available commands.");
                }
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void printHelp() {
        System.out.println("\nAvailable Commands:");
        System.out.println("  Config room count <N>");
        System.out.println("  Config room max capacity <RoomID> <Capacity>");
        System.out.println("  Block room <RoomID> <StartTime (HH:MM)> <Duration (mins)>");
        System.out.println("  Cancel room <RoomID>");
        System.out.println("  Add occupant <RoomID> <Count>");
        System.out.println("  Room status <RoomID>");
        System.out.println("  Exit / Quit / Help");
        System.out.println("\n*Mandatory first step: Config room count <N>");
    }

    /**
     * Parses the input string and creates the appropriate Command object.
     */
    private static Command parseCommand(String input) {
        Matcher matcher;

        try {
            if (input.equalsIgnoreCase("help")) {
                printHelp();
                return null;
            }

            // 1. Config room count
            matcher = CONFIG_ROOMS_PATTERN.matcher(input);
            if (matcher.matches()) {
                int count = Integer.parseInt(matcher.group(1));
                return new ConfigRoomsCommand(office, count);
            }

            // 2. Config room max capacity
            matcher = CONFIG_CAPACITY_PATTERN.matcher(input);
            if (matcher.matches()) {
                int roomId = Integer.parseInt(matcher.group(1));
                // Now accepts negative numbers; validation happens in ConfigCapacityCommand
                int capacity = Integer.parseInt(matcher.group(2)); 
                return new ConfigCapacityCommand(office, roomId, capacity);
            }

            // 3. Block room
            matcher = BLOCK_ROOM_PATTERN.matcher(input);
            if (matcher.matches()) {
                int roomId = Integer.parseInt(matcher.group(1));
                String startTime = matcher.group(2);
                int duration = Integer.parseInt(matcher.group(3));
                return new BookRoomCommand(office, roomId, startTime, duration);
            }
            
            // 4. Cancel room
            matcher = CANCEL_ROOM_PATTERN.matcher(input);
            if (matcher.matches()) {
                int roomId = Integer.parseInt(matcher.group(1));
                return new CancelBookingCommand(office, roomId);
            }

            // 5. Add occupant
            matcher = ADD_OCCUPANT_PATTERN.matcher(input);
            if (matcher.matches()) {
                int roomId = Integer.parseInt(matcher.group(1));
                // Now accepts negative numbers; validation happens in UpdateOccupancyCommand
                int count = Integer.parseInt(matcher.group(2)); 
                return new UpdateOccupancyCommand(office, roomId, count);
            }

            // 6. Room status
            matcher = ROOM_STATUS_PATTERN.matcher(input);
            if (matcher.matches()) {
                int roomId = Integer.parseInt(matcher.group(1));
                return new RoomStatusCommand(office, roomId);
            }

        } catch (NumberFormatException e) {
            // Handle cases where arguments are provided but are non-numeric strings (e.g., 'A' instead of '1')
            System.out.println("Invalid numeric input. Please check RoomID, Count, or Duration values.");
        }

        return null;
    }
}
