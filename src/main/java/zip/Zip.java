package zip;

/**
 * Zip class
 */
public class Zip {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor method for the Zip class
     */
    public Zip() {
        this.storage = new Storage("textfiles/tasks.txt");
        this.tasks = storage.load();
        this.ui = new Ui();
    }

    public String getWelcomeMessage() {
        return ui.showWelcome();
    }

    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();

        try {
            ParsedCommand command = Parser.parse(input);

            switch (command.type) {
            case BYE:
                response.append(ui.showMessage(" Bye. Hope to see you again soon!"));
                break;

            case LIST:
                response.append(ui.showTasks(tasks));
                break;

            case MARK: {
                int index = (int) command.args[0];
                checkIndex(index, tasks);
                tasks.get(index).markAsDone();
                storage.save(tasks);

                response.append(" Nice! I've marked this task as done:\n");
                response.append("  ").append(tasks.get(index));
                break;
            }

            case UNMARK: {
                int index = (int) command.args[0];
                checkIndex(index, tasks);
                tasks.get(index).markAsUndone();
                storage.save(tasks);

                response.append(" OK, I've marked this task as not done yet:\n");
                response.append("  ").append(tasks.get(index));
                break;
            }

            case TODO: {
                String desc = (String) command.args[0];

                Task t = new ToDo(desc);
                tasks.add(t);
                storage.save(tasks);

                response.append(" Got it. I've added this task:\n");
                response.append("  ").append(t).append("\n");
                response.append(" Now you have ").append(tasks.size())
                        .append(" tasks in the list.");
                break;
            }

            case DEADLINE: {
                String desc = (String) command.args[0];
                String by = (String) command.args[1];

                Task d = new Deadline(desc, by);
                tasks.add(d);
                storage.save(tasks);

                response.append(" Got it. I've added this task:\n");
                response.append("  ").append(d).append("\n");
                response.append(" Now you have ").append(tasks.size())
                        .append(" tasks in the list.");
                break;
            }

            case EVENT: {
                String desc = (String) command.args[0];
                String from = (String) command.args[1];
                String to = (String) command.args[2];

                Task e = new Event(desc, from, to);
                tasks.add(e);
                storage.save(tasks);

                response.append(" Got it. I've added this task:\n");
                response.append("  ").append(e).append("\n");
                response.append(" Now you have ").append(tasks.size())
                        .append(" tasks in the list.");
                break;
            }

            case DELETE: {
                int index = (int) command.args[0];
                checkIndex(index, tasks);

                response.append(" Noted. I've removed this task:\n");
                response.append("  ").append(tasks.get(index));

                tasks.remove(index);
                storage.save(tasks);
                break;
            }

            case FIND: {
                String keyword = (String) command.args[0];
                TaskList list = tasks.findTask(keyword);
                response.append(ui.showTasks(list));
                break;
            }

            default:
                response.append(" Sorry, I don't understand that command.");
            }

        } catch (ZipException e) {
            response.append(e.getMessage());
        }

        return response.toString();
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
