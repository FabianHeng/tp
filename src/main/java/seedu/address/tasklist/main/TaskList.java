package seedu.address.tasklist.main;

import java.util.ArrayList;
import java.util.Scanner;

import seedu.address.tasklist.commands.AddTask;
import seedu.address.tasklist.commands.CommandType;
import seedu.address.tasklist.commands.DayPlan;
import seedu.address.tasklist.commands.DeleteTask;
import seedu.address.tasklist.commands.Exit;
import seedu.address.tasklist.commands.Find;
import seedu.address.tasklist.commands.Help;
import seedu.address.tasklist.commands.ListCommand;
import seedu.address.tasklist.commands.Mark;
import seedu.address.tasklist.commands.Unmark;
import seedu.address.tasklist.exception.TaskListException;
import seedu.address.tasklist.storage.TaskStorage;
import seedu.address.tasklist.tasks.Task;
import seedu.address.tasklist.taskui.Ui;


/**
 * The main class for the TaskList application.
 * This class validates and processes commands.
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private final Scanner reader;

    /**
     * Constructs a new TaskList instance.
     * Initializes the task list by loading stored tasks and sets up a scanner for user input.
     *
     * @throws TaskListException if there is an error loading the stored tasks.
     */
    public TaskList() throws TaskListException {
        this.taskList = TaskStorage.loadList();
        this.reader = new Scanner(System.in);
    }

    /**
     * Processes the user's command and returns the result.
     *
     * @param userInput The input command string from the user.
     * @return The result message after executing the command.
     * @throws TaskListException If an invalid command or incorrect arguments are provided.
     */
    private String processCommand(String userInput) throws TaskListException {
        if (userInput == null || userInput.trim().isEmpty()) {
            throw new TaskListException("Input cannot be empty!");
        }

        String[] inputParts = userInput.trim().split(" ", 2);
        CommandType command = CommandType.fromString(inputParts[0]);

        if (command == CommandType.UNKNOWN) {
            throw new TaskListException("Unknown command.\nType \"help\" to see available commands.");
        }

        validateArguments(command, inputParts);


        switch (command) {
        case LIST:
            return ListCommand.execute(taskList);

        case MARK:
            String markResponse = Mark.execute(userInput, taskList);
            TaskStorage.updateList(taskList);
            return markResponse;

        case UNMARK:
            String unmarkResponse = Unmark.execute(userInput, taskList);
            TaskStorage.updateList(taskList);
            return unmarkResponse;

        case TODO:
            String todoResponse = AddTask.todo(userInput, taskList);
            TaskStorage.updateList(taskList);
            return todoResponse;

        case DEADLINE:
            String deadlineResponse = AddTask.deadline(userInput, taskList);
            TaskStorage.updateList(taskList);
            return deadlineResponse;

        case EVENT:
            String eventResponse = AddTask.event(userInput, taskList);
            TaskStorage.updateList(taskList);
            return eventResponse;

        case DELETE:
            String deleteResponse = DeleteTask.execute(userInput, taskList);
            TaskStorage.updateList(taskList);
            return deleteResponse;

        case FIND:
            return Find.execute(userInput, taskList);

        case DAYPLAN:
            return DayPlan.execute(userInput, taskList);

        case HELP:
            return Help.execute();

        case EXIT:
            return Exit.execute();

        default:
            throw new TaskListException("Unknown command.");
        }
    }

    /**
     * Handles user input from the GUI and returns the response to be displayed.
     *
     * @param input The user's input string.
     * @return The response string from TaskList.
     */
    public String getResponse(String input) {
        try {
            return processCommand(input);
        } catch (TaskListException e) {
            return e.getMessage(); // Return error messages to GUI
        }
    }

    /**
     * Runs the TaskList application in the console.
     * Continuously listens for user commands until the user exits.
     */
    public void run() {
        Ui.showWelcomeMessage();

        while (true) {
            String userInput = reader.nextLine();
            try {
                String response = processCommand(userInput);
                Ui.showMessage(response);

                if (CommandType.fromString(userInput.split(" ")[0]) == CommandType.EXIT) {
                    Ui.showExitMessage();
                    return;
                }

            } catch (TaskListException e) {
                Ui.showMessage(e.getMessage());
            }
        }
    }

    /**
     * Validates the arguments provided for a given command.
     * Ensures the user input meets the expected format for each command.
     *
     * @param command The command type provided by the user.
     * @param inputParts The split user input containing command and arguments.
     * @throws TaskListException if the provided arguments are incorrect or missing.
     */
    private void validateArguments(CommandType command, String[] inputParts) throws TaskListException {
        int argLength = inputParts.length;

        switch (command) {
        case LIST:
        case HELP:
        case EXIT:
            if (argLength != 1) {
                throw new TaskListException(command + " command should not have any arguments.");
            }
            break;

        case MARK:
        case UNMARK:
        case DELETE:
        case DAYPLAN:
            if (argLength != 2) {
                throw new TaskListException(command + " command requires exactly one argument.");
            }
            break;

        case FIND:
            if (argLength < 2 || inputParts[1].trim().isEmpty()) {
                throw new TaskListException("FIND command must include a keyword.");
            }
            break;

        case TODO:
            if (argLength != 2 || inputParts[1].trim().isEmpty()) {
                throw new TaskListException("TODO command must include a task description.");
            }
            break;

        case DEADLINE:
            if (argLength < 2 || !inputParts[1].contains("/by")) {
                throw new TaskListException("DEADLINE format: deadline <task> /by <date>");
            }
            break;

        case EVENT:
            if (argLength < 2 || !inputParts[1].contains("/from") || !inputParts[1].contains("/to")) {
                throw new TaskListException("EVENT format: event <task> /from <start> /to <end>");
            }
            break;

        default:
            throw new TaskListException("Invalid command or format.");
        }
    }

    /**
     * The entry point for the TaskList application.
     * Initializes and runs the application.
     *
     * @param args Command-line arguments (not used yet).
     */
    public static void main(String[] args) {
        try {
            new TaskList().run();
        } catch (TaskListException e) {
            System.out.println("An error occurred while starting the program: " + e.getMessage());
        }
    }
}
