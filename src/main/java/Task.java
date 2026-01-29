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
        return "Task" + "|" + (isDone ? "1" : "0") + "|" + description;
    }

    @Override
    public String toString() {
        return "[ ] " + description;
    }
}
