package duke;

/**
 * Represents a generic task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task marked as undone.
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if done, otherwise a space
     */
    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the string representation used for file storage.
     *
     * @return Formatted file string
     */
    public String toFileString() {
        return "duke.Task" + "|" + (isDone ? "1" : "0") + "|" + description;
    }

    /**
     * Creates a task from a stored file string.
     *
     * @param fileString Stored task string
     * @return Parsed Task object
     */
    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");

        Task task = null;
        boolean isDone = parts[1].equals("1");

        switch (parts[0]) {
        case "Todo":
            task = new ToDo(parts[2]);
            break;
        case "duke.Deadline":
            task = new Deadline(parts[2], parts[3]);
            break;
        case "duke.Event":
            task = new Event(parts[2], parts[3], parts[4]);
            break;
        default:
            break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Returns a user-friendly string representation of the task.
     *
     * @return Formatted task string
     */
    @Override
    public String toString() {
        return "[ ] " + description;
    }
}
