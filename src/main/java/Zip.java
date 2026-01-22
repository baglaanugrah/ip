import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zip {

    private static List<String> list = new ArrayList<>();

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
            } else if (input.equalsIgnoreCase("list"))  {
                int i = 1;
                for (String s : Zip.list) {
                    System.out.println(i + "." + " " + s);
                    i++;
                }
            } else {
                System.out.println(" " + "added: " + input);
                Zip.list.add(input);
                printLine();
            }
        }

        scanner.close();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

