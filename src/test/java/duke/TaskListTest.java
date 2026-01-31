package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

public class TaskListTest {

    TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void get_validIndex_returnsCorrectTask() throws ZipException {
        Task task = new ToDo("do homework");
        taskList.add(task);

        Task retrieved = taskList.get(0);

        assertEquals(task, retrieved);
    }

    @Test
    public void get_invalidIndex_throwsZipException() {
        assertThrows(ZipException.class, () -> taskList.get(0));
    }

    @Test
    public void add_one_sizeIncreases() {
        Task task = new ToDo("do laundry");
        taskList.add(task);

        assertEquals(1, taskList.size());
    }
}
