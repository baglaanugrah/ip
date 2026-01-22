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
            }

            else if (input.equalsIgnoreCase("list")) {
                if (list.isEmpty()) {
                    System.out.println("There are no tasks in the list");
                } else {
                    System.out.println(" Here are the tasks in your list:");
                    int i = 1;
                    for (Task t : list) {
                        System.out.println(" " + i + "." + t);
                        i++;
                    }
                }
            }

            else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < list.size()) {
                    list.get(index).markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("  " + list.get(index));
                } else {
                    System.out.println(" Invalid task number.");
                }
            }

            else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < list.size()) {
                    list.get(index).markAsUndone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("  " + list.get(index));
                } else {
                    System.out.println(" Invalid task number.");
                }
            }

            else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                Task t = new ToDo(description);
                list.add(t);

                System.out.println(" Got it. I've added this task:");
                System.out.println("  " + t);
                System.out.println(" Now you have " + list.size() + " tasks in the list.");
            }

            else if (input.startsWith("deadline ")) {
                String content = input.substring(9);
                String[] parts = content.split(" /by ", 2);

                String description = parts[0];
                String by = parts[1];

                Task d = new Deadline(description, by);
                list.add(d);

                System.out.println(" Got it. I've added this task:");
                System.out.println("  " + d);
                System.out.println(" Now you have " + list.size() + " tasks in the list.");
            }

            else if (input.startsWith("event ")) {
                String content = input.substring(6);
                String[] firstSplit = content.split(" /from ", 2);

                String description = firstSplit[0];
                String[] secondSplit = firstSplit[1].split(" /to ", 2);

                String from = secondSplit[0];
                String to = secondSplit[1];

                Task e = new Event(description, from, to);
                list.add(e);

                System.out.println(" Got it. I've added this task:");
                System.out.println("  " + e);
                System.out.println(" Now you have " + list.size() + " tasks in the list.");
            }

            else {
                System.out.println(" Sorry, I don't understand that command.");
            }

            printLine();
        }

        scanner.close();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
