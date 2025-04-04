package seedu.address.tasklist.commands;

/**
 * Represents the different types of commands that can be executed.
 */
public enum CommandType {
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    DAYPLAN,
    HELP,
    FIND,
    EXIT,
    UNKNOWN; // For unrecognized commands

    /**
     * Converts a user input string into a corresponding CommandType.
     *
     * @param input The user input command as a string.
     * @return The corresponding CommandType.
     */
    public static CommandType fromString(String input) {
        switch (input.toLowerCase()) {
        case "list":
            return LIST;
        case "mark":
            return MARK;
        case "unmark":
            return UNMARK;
        case "todo":
            return TODO;
        case "deadline":
            return DEADLINE;
        case "event":
            return EVENT;
        case "delete":
            return DELETE;
        case "agenda":
            return DAYPLAN;
        case "help":
            return HELP;
        case "find":
            return FIND;
        case "exit":
            return EXIT;
        default:
            return UNKNOWN;
        }
    }
}
