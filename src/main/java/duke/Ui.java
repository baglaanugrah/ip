package duke;

import java.util.Scanner;

/**
 * Handles user interaction and display.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        printLine();
        System.out.println(" Hello! I'm duke.Zip");
        System.out.println(" What can I do for you?");
        printLine();
    }

    /**
     * Reads a command from the user.
     *
     * @return User input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        printLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message Message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays all tasks in the list.
     *
     * @param taskList TaskList to display
     */
    public void showTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("There are no tasks in the list");
            return;
        }

        System.out.println(" Here are the tasks in your list:");
        int i = 1;
        for (Task t : taskList.getTasks()) {
            System.out.println(" " + i + "." + t);
            i++;
        }
    }

    /**
     * Prints a horizontal separator line.
     */
    private void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Closes input resources.
     */
    public void close() {
        scanner.close();
    }
}
