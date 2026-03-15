package cli.commands;

public class ExitCommand implements Command {
    private final String name = "exit";

    @Override
    public String name() {
        return name;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0 && args[0].equals("help")) {
            System.out.println(help());
            return;
        }

        System.out.println("Exiting the application. Ahoj!");
        System.exit(0);
    }

    @Override
    public String help() {
        return "Usage: %s -- Exits the application.".formatted(name);
    }
}
