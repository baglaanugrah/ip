package duke;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private final List<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index Index of the task
     * @return Task at the specified index
     * @throws ZipException If index is out of bounds
     */
    public Task get(int index) throws ZipException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZipException("Index out of bounds!");
        }
        return tasks.get(index);
    }

    /**
     * Removes the task at the given index.
     *
     * @param index Index of the task
     * @throws ZipException If index is out of bounds
     */
    public void remove(int index) throws ZipException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZipException("Index out of bounds!");
        }
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether the list is empty.
     *
     * @return True if empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the underlying task list.
     *
     * @return List of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }


    /**
     * Returns the list of tasks whose description contain the keyword.
     *
     * @param keyword word to find
     * @return list of tasks
     */
    public TaskList findTask(String keyword) {
        TaskList result = new TaskList();

        String lowerKeyword = keyword.toLowerCase();

        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(lowerKeyword)) {
                result.add(task);
            }
        }

        return result;
    }

}
