package cli;

import cli.commands.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandDispatcher {
    /**
     * Map of command name to Command instance for quick lookup
     */
    private final Map<String, Command> commands =  new HashMap<>();

    public CommandDispatcher(List<Command> commands) {
        for (Command command : commands) {
            this.commands.put(command.name(), command);
        }
    }

    public void dispatch(ParsedCommand parsedCommand) {
        Command command = commands.get(parsedCommand.commandName());

        if (command == null) {
            System.out.printf("Unknown command: '%s'%n", parsedCommand.commandName());
            return;
        }

        command.execute(parsedCommand.args());
    }

    public List<Command> getCommands() {
        return new ArrayList<>(commands.values());
    }
}
