import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Zip {

    private static List<Task> list = new ArrayList<>();

    public static void main(String[] args) throws ZipException {

        File file = new File("textfiles/tasks.txt");
        System.out.println("Reading file: " + file.getAbsolutePath());
        Zip.loadFileContent(file);

        Scanner scanner = new Scanner(System.in);

        printLine();
        System.out.println(" Hello! I'm Zip");
        System.out.println(" What can I do for you?");
        printLine();

        while (true) {
            String input = scanner.nextLine();
            printLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (input.equalsIgnoreCase("list")) {
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
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                try {
                    if (index >= 0 && index < list.size()) {
                        list.get(index).markAsDone();
                        Zip.saveToFile(file);
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("  " + list.get(index));
                    } else {
                        throw new ZipException("Invalid index");
                    }
                } catch (ZipException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                try {
                    if (index >= 0 && index < list.size()) {
                        list.get(index).markAsUndone();
                        Zip.saveToFile(file);
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("  " + list.get(index));
                    } else {
                        throw new ZipException("Invalid index");
                    }
                } catch (ZipException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("todo ")) {
                try {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new ZipException("Nothing to add to list");
                    }
                    Task t = new ToDo(description);
                    list.add(t);
                    Zip.saveToFile(file);

                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                } catch (ZipException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("deadline ")) {
                try {
                    String content = input.substring(9).trim();
                    if (content.isEmpty()) {
                        throw new ZipException("Nothing to add to list");
                    }
                    String[] parts = content.split(" /by ", 2);

                    String description = parts[0];
                    String by = parts[1];

                    Task d = new Deadline(description, by);
                    list.add(d);
                    Zip.saveToFile(file);

                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  " + d);
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                } catch (ZipException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("event ")) {
                try {
                    String content = input.substring(6).trim();
                    if (content.isEmpty()) {
                        throw new ZipException("Nothing to add to list");
                    }
                    String[] firstSplit = content.split(" /from ", 2);

                    String description = firstSplit[0];
                    String[] secondSplit = firstSplit[1].split(" /to ", 2);

                    String from = secondSplit[0];
                    String to = secondSplit[1];

                    Task e = new Event(description, from, to);
                    list.add(e);
                    Zip.saveToFile(file);

                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  " + e);
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                } catch (ZipException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("delete ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < list.size()) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + list.get(index));
                        list.remove(index);
                        Zip.saveToFile(file);
                    } else {
                        throw new ZipException("Invalid index");
                    }
                } catch (ZipException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                System.out.println(" Sorry, I don't understand that command.");
            }

            printLine();
        }

        scanner.close();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void loadFileContent(File file) throws ZipException {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return;
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");

                Task task = null;
                boolean isDone = parts[1].equals("1");

                switch (parts[0]) {
                    case "Todo":
                        task = new ToDo(parts[2]);
                        break;
                    case "Deadline":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "Event":
                        task = new Event(parts[2], parts[3], parts[4]);
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    list.add(task);
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            throw new ZipException(e.getMessage());
        }
    }

    private static void saveToFile(File file) throws ZipException {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task t : list) {
                fw.write(t.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new ZipException(e.getMessage());
        }
    }

}
