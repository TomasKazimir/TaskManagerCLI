package cli.commands;


import model.Task;
import service.TaskService;

public class AddTaskCommand implements Command {
    private final TaskService taskService;
    private final String name = "add";

    public AddTaskCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0 || args[0].equals("help")) {
            System.out.println(help());
            return;
        }

        String description = String.join(" ", args);
        Task created = taskService.createTask(description);

        System.out.println("Task added: " + created.getId() + " - " + created.getDescription());
    }

    @Override
    public String help() {
        return "Usage: %s <description> -- Adds a new task with the given description.".formatted(name);
    }
}
