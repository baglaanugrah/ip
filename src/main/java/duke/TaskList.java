package duke;

import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private final List<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) throws ZipException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZipException("Index out of bounds!");
        }
        return tasks.get(index);
    }

    public void remove(int index) throws ZipException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZipException("Index out of bounds!");
        }
        tasks.remove(index);
    }

    public int  size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
