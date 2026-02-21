package zip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start and end date.
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Creates an Event task with a description, start date, and end date.
     *
     * @param description Task description
     * @param from Start date in yyyy-MM-dd format
     * @param to End date in yyyy-MM-dd format
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the string representation used for file storage.
     *
     * @return Formatted file string
     */
    @Override
    public String toFileString() {
        return "duke.Event" + "|" + (isDone ? "1" : "0")
                + "|" + description + "|" + from + "|" + to;
    }

    /**
     * Returns a user-friendly string representation of the task.
     *
     * @return Formatted task string
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description
                + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
