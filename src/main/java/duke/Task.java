package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String toFileString() {
        return "duke.Task" + "|" + (isDone ? "1" : "0") + "|" + description;
    }

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
        }

        if (task != null) {
            if (isDone) {
                task.markAsDone();
            }
        }
        return task;
    }

    @Override
    public String toString() {
        return "[ ] " + description;
    }
}
