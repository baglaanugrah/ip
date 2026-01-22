import java.util.Scanner;

public class Zip {
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
            } else {
                System.out.println(" " + input);
                printLine();
            }
        }

        scanner.close();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

