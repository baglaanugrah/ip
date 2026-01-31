package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 * Handles loading and saving tasks to disk.
 */
public class Storage {
    private final File file;

    /**
     * Creates a Storage object using the given file path.
     *
     * @param filePath Path to the storage file
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return TaskList containing loaded tasks
     * @throws ZipException If an I/O error occurs
     */
    public TaskList load() throws ZipException {
        TaskList taskList = new TaskList();

        if (!file.exists()) {
            return taskList;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                taskList.add(Task.fromFileString(line));
            }
        } catch (IOException e) {
            throw new ZipException("Error loading file");
        }

        return taskList;
    }

    /**
     * Saves the tasks to the storage file.
     *
     * @param taskList TaskList to be saved
     * @throws ZipException If an I/O error occurs
     */
    public void save(TaskList taskList) throws ZipException {
        try {
            file.getParentFile().mkdirs();
            try (FileWriter fw = new FileWriter(file)) {
                for (Task t : taskList.getTasks()) {
                    fw.write(t.toFileString() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new ZipException("Error saving file");
        }
    }
}
