package cli;

public class CommandParser {
    /**
     * @param input the raw input string from the user, e.g. "add Buy milk"
     * @return a ParsedCommand object containing the command name and its arguments, e.g. commandName="add", args=["Buy", "milk"]
     */
    public static ParsedCommand parse(String input) {
        if (input == null || input.isEmpty()) {
            return new ParsedCommand("", new String[0]);
        }

        String[] parts = input.trim().split("\\s+");

        String commandName = parts[0].toLowerCase();

        if (parts.length == 1) {
            return new ParsedCommand(commandName, new String[0]);
        }

        String[] commandArgs = new String[parts.length - 1];
        System.arraycopy(parts, 1, commandArgs, 0, parts.length - 1);

        return new ParsedCommand(commandName, commandArgs);
    }
}
