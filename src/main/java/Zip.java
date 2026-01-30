import java.util.Scanner;

public class Zip {



    public static void main(String[] args) throws ZipException {

        TaskList tasks;

        Storage storage = new Storage("textfiles/tasks.txt");
        tasks = storage.load();

        Ui ui = new Ui();

        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();
            ui.showLine();

            if (input.equalsIgnoreCase("bye")) {
                ui.showMessage(" Bye. Hope to see you again soon!");
                ui.showLine();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                if (tasks.isEmpty()) {
                    ui.showMessage("There are no tasks in the list");
                } else {
                    ui.showMessage(" Here are the tasks in your list:");
                    int i = 1;
                    for (Task t : tasks.getTasks()) {
                        ui.showMessage(" " + i + "." + t);
                        i++;
                    }
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                try {
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        storage.save(tasks);
                        ui.showMessage(" Nice! I've marked this task as done:");
                        ui.showMessage("  " + tasks.get(index));
                    } else {
                        throw new ZipException("Invalid index");
                    }
                } catch (ZipException e) {
                    ui.showMessage(e.getMessage());
                }
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                try {
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsUndone();
                        storage.save(tasks);
                        ui.showMessage(" OK, I've marked this task as not done yet:");
                        ui.showMessage("  " + tasks.get(index));
                    } else {
                        throw new ZipException("Invalid index");
                    }
                } catch (ZipException e) {
                    ui.showMessage(e.getMessage());
                }
            } else if (input.startsWith("todo ")) {
                try {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new ZipException("Nothing to add to list");
                    }
                    Task t = new ToDo(description);
                    tasks.add(t);
                    storage.save(tasks);
                    ui.showMessage(" Got it. I've added this task:");
                    ui.showMessage("  " + t);
                    ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
                } catch (ZipException e) {
                    ui.showMessage(e.getMessage());
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
                    tasks.add(d);
                    storage.save(tasks);

                    ui.showMessage(" Got it. I've added this task:");
                    ui.showMessage("  " + d);
                    ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
                } catch (ZipException e) {
                    ui.showMessage(e.getMessage());
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
                    tasks.add(e);
                    storage.save(tasks);

                    ui.showMessage(" Got it. I've added this task:");
                    ui.showMessage("  " + e);
                    ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
                } catch (ZipException e) {
                    ui.showMessage(e.getMessage());
                }
            } else if (input.startsWith("delete ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        ui.showMessage("Noted. I've removed this task:");
                        ui.showMessage("  " + tasks.get(index));
                        tasks.remove(index);
                        storage.save(tasks);
                    } else {
                        throw new ZipException("Invalid index");
                    }
                } catch (ZipException e) {
                    ui.showMessage(e.getMessage());
                }

            } else {
                ui.showMessage(" Sorry, I don't understand that command.");
            }

            printLine();
        }
        ui.close();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

}
