import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zip {

    private static List<Task> list = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        printLine();
        System.out.println(" Hello! I'm Zip");
        System.out.println(" What can I do for you?");
        printLine();

        while (true) {
            String input = scanner.nextLine().trim();

            printLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                int i = 1;
                if (Zip.list.isEmpty()) {
                    System.out.println("There are no tasks in the list");
                }
                for (Task s : Zip.list) {
                    System.out.println(i + "." + " " + "[" + s.getStatusIcon() + "]" + " " + s.getDescription());
                    i++;
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;

                if (index >= 0 && index < Zip.list.size()) {
                    Zip.list.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + Zip.list.get(index).getDescription());

                } else {
                    System.out.println(" Invalid task number.");
                }

            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;

                if (index >= 0 && index < Zip.list.size()) {
                    Zip.list.get(index).markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[] " + Zip.list.get(index).getDescription());
                } else {
                    System.out.println(" Invalid task number.");
                }
            } else {
                Task t = new Task(input);
                System.out.println(" " + "added: " + input);
                Zip.list.add(t);
                printLine();
            }
        }

        scanner.close();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

