package duke;

/**
 * Zip class
 */
public class Zip {

    public static void main(String[] args) {
        Storage storage = new Storage("textfiles/tasks.txt");
        TaskList tasks = storage.load();
        Ui ui = new Ui();

        ui.showWelcome();

        while (true) {
            try {
                String input = ui.readCommand();
                ui.showLine();

                ParsedCommand command = Parser.parse(input);

                switch (command.type) {
                case BYE:
                    ui.showMessage(" Bye. Hope to see you again soon!");
                    ui.showLine();
                    ui.close();
                    return;

                case LIST:
                    ui.showTasks(tasks);
                    break;

                case MARK: {
                    int index = (int) command.args[0];
                    checkIndex(index, tasks);
                    tasks.get(index).markAsDone();
                    storage.save(tasks);
                    ui.showMessage(" Nice! I've marked this task as done:");
                    ui.showMessage("  " + tasks.get(index));
                    break;
                }

                case UNMARK: {
                    int index = (int) command.args[0];
                    checkIndex(index, tasks);
                    tasks.get(index).markAsUndone();
                    storage.save(tasks);
                    ui.showMessage(" OK, I've marked this task as not done yet:");
                    ui.showMessage("  " + tasks.get(index));
                    break;
                }

                case TODO: {
                    String desc = (String) command.args[0];
                    Task t = new ToDo(desc);
                    tasks.add(t);
                    storage.save(tasks);
                    ui.showMessage(" Got it. I've added this task:");
                    ui.showMessage("  " + t);
                    ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
                    break;
                }

                case DEADLINE: {
                    String desc = (String) command.args[0];
                    String by = (String) command.args[1];
                    Task d = new Deadline(desc, by);
                    tasks.add(d);
                    storage.save(tasks);
                    ui.showMessage(" Got it. I've added this task:");
                    ui.showMessage("  " + d);
                    ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
                    break;
                }

                case EVENT: {
                    String desc = (String) command.args[0];
                    String from = (String) command.args[1];
                    String to = (String) command.args[2];
                    Task e = new Event(desc, from, to);
                    tasks.add(e);
                    storage.save(tasks);
                    ui.showMessage(" Got it. I've added this task:");
                    ui.showMessage("  " + e);
                    ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
                    break;
                }

                case DELETE: {
                    int index = (int) command.args[0];
                    checkIndex(index, tasks);
                    ui.showMessage(" Noted. I've removed this task:");
                    ui.showMessage("  " + tasks.get(index));
                    tasks.remove(index);
                    storage.save(tasks);
                    break;
                }

                case FIND: {
                    String keyword = (String) command.args[0];
                    TaskList list = tasks.findTask(keyword);
                    ui.showTasks(list);
                    break;
                }

                default:
                    ui.showMessage(" Sorry, I don't understand that command.");

                }

            } catch (ZipException e) {
                ui.showMessage(e.getMessage());
            }

            ui.showLine();
        }
    }

    /**
     * Checks if the input index is valid.
     *
     * @param index Input index
     * @param tasks Task list
     * @throws ZipException if the index is less than 0 or more than or equal to the list size
     */
    private static void checkIndex(int index, TaskList tasks) throws ZipException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZipException("Invalid task number");
        }
    }
}
