package cli.commands;


import model.Task;
import service.TaskService;

import java.util.List;

public class ListTasksCommand implements Command {
    private final TaskService taskService;
    private final String name = "list";

    public ListTasksCommand(TaskService taskService) {
        this.taskService = taskService;
    }

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

        List<Task> tasks = taskService.listTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    @Override
    public String help() {
        return "Usage: %s -- Lists all tasks in the system.".formatted(name);
    }
}
