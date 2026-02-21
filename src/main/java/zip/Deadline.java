package zip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents a task with a due date.
 */
public class Deadline extends Task {
    private final LocalDate by;

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
     * Returns the date of the deadline task
     *
     * @return Optional instance of the date of the deadline
     */
    @Override
    public Optional<LocalDate> getDeadlineDate() {
        return Optional.of(this.by);
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

