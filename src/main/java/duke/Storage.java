package duke;

import java.io.*;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

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
