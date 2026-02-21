package duke;

/**
 * Parses user input into executable commands.
 */
public class Parser {

    /**
     * Parses the user input and returns a corresponding ParsedCommand.
     *
     * @param input Raw user input
     * @return ParsedCommand representing the input
     * @throws ZipException If the input format is invalid or unrecognized
     */
    public static ParsedCommand parse(String input) throws ZipException {
        if (input.equalsIgnoreCase("bye")) {
            return new ParsedCommand(CommandType.BYE);
        }

        if (input.equalsIgnoreCase("list")) {
            return new ParsedCommand(CommandType.LIST);
        }

        if (input.startsWith("mark ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            assert index >= 0 : "index should be greater than or equal to zero";
            return new ParsedCommand(CommandType.MARK, index);
        }

        if (input.startsWith("unmark ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            assert index >= 0 : "index should be greater than or equal to zero";
            return new ParsedCommand(CommandType.UNMARK, index);
        }

        if (input.startsWith("todo ")) {
            String desc = input.substring(5).trim();
            if (desc.isEmpty()) {
                throw new ZipException("Nothing to add to list");
            }
            return new ParsedCommand(CommandType.TODO, desc);
        }

        if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ", 2);
            assert parts.length == 2 : "invalid deadline command";
            return new ParsedCommand(CommandType.DEADLINE, parts[0], parts[1]);
        }

        if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /from | /to ");
            assert parts.length == 3 : "invalid event command";
            return new ParsedCommand(CommandType.EVENT, parts[0], parts[1], parts[2]);
        }

        if (input.startsWith("delete ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new ParsedCommand(CommandType.DELETE, index);
        }

        if (input.startsWith("find ")) {
            String keyword = input.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new ZipException("Nothing to find");
            }
            return new ParsedCommand(CommandType.FIND, keyword);
        }

        throw new ZipException("Sorry, I don't understand that command.");
    }
}
