package zip;


/**
 * Handles user interaction and display.
 */
public class Ui {
    private static final String LINE =
            "____________________________________________________________";
    /**
     * Displays the welcome message.
     *
     * @return the welcome message
     */
    public String showWelcome() {
        return LINE + "\n"
                + " Hello! I'm duke.Zip\n"
                + " What can I do for you?\n"
                + LINE;
    }

    /**
     * Displays a message to the user.
     *
     * @param message Message to display
     * @return the message
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Displays all tasks in the list.
     *
     * @param taskList TaskList to display\
     * @return the formatted Tasklist
     */
    public String showTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "There are no tasks in the list";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(" Here are the tasks in your list:\n");

        int i = 1;
        for (Task t : taskList.getTasks()) {
            sb.append(" ").append(i).append(".").append(t).append("\n");
            i++;
        }

        return sb.toString().trim();
    }

    /**
     * Returns the upcoming deadline tasks
     *
     * @param taskList current Tasklist
     * @return the string representation of the upcoming deadline tasks
     */
    public String showDeadlines(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "There are no upcoming deadlines";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(" Here are the upcoming deadlines sorted in chronological order:\n");

        int i = 1;
        for (Task t : taskList.getDeadlines()) {
            sb.append(" ").append(i).append(".").append(t).append("\n");
            i++;
        }

        return sb.toString().trim();
    }
}
