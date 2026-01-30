public class ParsedCommand {
    public final CommandType type;
    public final Object[] args;

    public ParsedCommand(CommandType type, Object... args) {
        this.type = type;
        this.args = args;
    }
}