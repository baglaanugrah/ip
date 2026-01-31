package duke;

/**
 * Represents a parsed user command.
 */
public class ParsedCommand {
    public final CommandType type;
    public final Object[] args;

    /**
     * Creates a ParsedCommand with the given type and arguments.
     *
     * @param type Command type
     * @param args Command arguments
     */
    public ParsedCommand(CommandType type, Object... args) {
        this.type = type;
        this.args = args;
    }
}
