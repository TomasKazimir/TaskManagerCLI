
import cli.CommandDispatcher;
import cli.CommandParser;
import cli.ParsedCommand;
import cli.commands.*;
import repository.InMemTaskRepository;
import repository.TaskRepository;
import service.TaskService;

import java.util.List;
import java.util.Scanner;

public class TaskManagerApp {
    static void main(String[] args) {
        TaskRepository taskRepository = new InMemTaskRepository();
        TaskService taskService = new TaskService(taskRepository);

        CommandDispatcher dispatcher = new CommandDispatcher(
            List.of(
                new AddTaskCommand(taskService),
                new ListTasksCommand(taskService),
                new CompleteTaskCommand(taskService),
                new DeleteTaskCommand(taskService),
                new ExitCommand()
            )
        );

        printHelp(dispatcher);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            ProcessInputLine(scanner.nextLine(), dispatcher);
        }
    }

    static void printHelp(CommandDispatcher dispatcher) {
        System.out.println("main.TaskManagerApp");
        System.out.println("Available commands:");
        for (Command command : dispatcher.getCommands()) {
            System.out.println(command.name());
            System.out.println("\t" + command.help());
        }
        System.out.println();
    }

    static void ProcessInputLine(String input, CommandDispatcher dispatcher) {
        ParsedCommand parsed = CommandParser.parse(input);
        dispatcher.dispatch(parsed);
    }
}
