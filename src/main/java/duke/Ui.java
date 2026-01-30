package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        printLine();
        System.out.println(" Hello! I'm duke.Zip");
        System.out.println(" What can I do for you?");
        printLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        printLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

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

    private void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void close() {
        scanner.close();
    }
}

