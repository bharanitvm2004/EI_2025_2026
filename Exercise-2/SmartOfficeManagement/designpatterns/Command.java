package designpatterns;

import java.util.List;

/**
 * Command interface: Encapsulates all operations (booking, configuration, occupancy updates).
 * This allows for flexible invocation and easy extension of new operations.
 */
public interface Command {
    /**
     * Executes the command.
     * @return A list of strings containing messages/results of the execution.
     */
    List<String> execute();
}
