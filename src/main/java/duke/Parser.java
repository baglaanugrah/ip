package duke;

public class Parser {

    public static ParsedCommand parse(String input) throws ZipException {
        if (input.equalsIgnoreCase("bye")) {
            return new ParsedCommand(CommandType.BYE);
        }

        if (input.equalsIgnoreCase("list")) {
            return new ParsedCommand(CommandType.LIST);
        }

        if (input.startsWith("mark ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new ParsedCommand(CommandType.MARK, index);
        }

        if (input.startsWith("unmark ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new ParsedCommand(CommandType.UNMARK, index);
        }

        if (input.startsWith("todo ")) {
            String desc = input.substring(5).trim();
            if (desc.isEmpty()) throw new ZipException("Nothing to add to list");
            return new ParsedCommand(CommandType.TODO, desc);
        }

        if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ", 2);
            return new ParsedCommand(CommandType.DEADLINE, parts[0], parts[1]);
        }

        if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /from | /to ");
            return new ParsedCommand(CommandType.EVENT, parts[0], parts[1], parts[2]);
        }

        if (input.startsWith("delete ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new ParsedCommand(CommandType.DELETE, index);
        }

        throw new ZipException("Sorry, I don't understand that command.");
    }
}
