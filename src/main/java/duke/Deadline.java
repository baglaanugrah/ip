package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a due date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a Deadline task with the given description and due date.
     *
     * @param description Task description
     * @param by Due date in yyyy-MM-dd format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the string representation used for file storage.
     *
     * @return Formatted file string
     */
    @Override
    public String toFileString() {
        return "duke.Deadline" + "|" + (isDone ? "1" : "0")
                + "|" + description + "|" + by;
    }

    /**
     * Returns a user-friendly string representation of the task.
     *
     * @return Formatted task string
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

