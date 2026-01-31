package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {

    @Test
    public void parse_deadlineCommand() throws ZipException {
        ParsedCommand cmd = Parser.parse("deadline submit report /by 2024-10-01");

        assertEquals(CommandType.DEADLINE, cmd.type);
        assertEquals("submit report", cmd.args[0]);
        assertEquals("2024-10-01", cmd.args[1]);
    }

    @Test
    public void parse_eventCommand() throws ZipException {
        ParsedCommand cmd =
                Parser.parse("event meeting /from 2pm /to 4pm");

        assertEquals(CommandType.EVENT, cmd.type);
        assertEquals("meeting", cmd.args[0]);
        assertEquals("2pm", cmd.args[1]);
        assertEquals("4pm", cmd.args[2]);
    }

    @Test
    public void parse_todoCommand() throws ZipException {
        ParsedCommand cmd = Parser.parse("todo laundry");

        assertEquals(CommandType.TODO, cmd.type);
        assertEquals("laundry", cmd.args[0]);

    }

}
