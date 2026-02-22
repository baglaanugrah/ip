package zip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Parses user input into executable commands.
 */
public class Parser {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                                                                            .withResolverStyle(ResolverStyle.STRICT);

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

        if (input.equalsIgnoreCase("due")) {
            return new ParsedCommand(CommandType.DUE);
        }

        if (input.startsWith("mark ")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                return new ParsedCommand(CommandType.MARK, index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new ZipException("Invalid Input");
            }
        }

        if (input.startsWith("unmark ")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                return new ParsedCommand(CommandType.UNMARK, index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new ZipException("Invalid Input");
            }
        }

        if (input.startsWith("todo ")) {
            String desc = input.substring(5).trim();
            if (desc.isEmpty()) {
                throw new ZipException("Nothing to add to list");
            }
            return new ParsedCommand(CommandType.TODO, desc);
        }

        if (input.startsWith("deadline ")) {
            try {
                String[] parts = input.substring(9).split(" /by ", 2);
                if (parts.length != 2) {
                    throw new ZipException("Invalid Input");
                }

                LocalDate.parse(parts[1], dateFormatter);

                return new ParsedCommand(CommandType.DEADLINE, parts[0], parts[1]);
            } catch (DateTimeParseException e) {
                throw new ZipException("Invalid date format");
            }
        }

        if (input.startsWith("event ")) {
            try {
                String[] parts = input.substring(6).split(" /from | /to ");
                if (parts.length != 3) {
                    throw new ZipException("Invalid Input");
                }

                LocalDate fromDate = LocalDate.parse(parts[1], dateFormatter);
                LocalDate toDate = LocalDate.parse(parts[2], dateFormatter);

                if (fromDate.isAfter(toDate)) {
                    throw new ZipException("The start date cannot be after the end date.");
                }

                return new ParsedCommand(CommandType.EVENT, parts[0], parts[1], parts[2]);

            } catch (DateTimeParseException e) {
                throw new ZipException("Invalid date format.");
            }
        }

        if (input.startsWith("delete ")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                return new ParsedCommand(CommandType.DELETE, index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new ZipException("Invalid input");
            }
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
