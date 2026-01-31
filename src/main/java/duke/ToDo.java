package duke;

/**
 * Represents a task with only a description
 */
public class ToDo extends Task {

    /**
     * Creates a to-do task with a description
     *
     * @param description Task description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation used for file storage.
     *
     * @return Formatted file string
     */
    @Override
    public String toFileString() {
        return "Todo" + "|" + (isDone ? "1" : "0") + "|" + description;
    }

    /**
     * Returns a user-friendly string representation of the task.
     *
     * @return Formatted task string
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
