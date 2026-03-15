package cli.commands;

public interface Command {
    String name();

    void execute(String[] args);

    String help();
}
