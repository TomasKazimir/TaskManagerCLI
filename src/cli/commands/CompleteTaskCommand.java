package cli.commands;

import cli.Util;
import service.TaskService;

import java.util.Optional;

public class CompleteTaskCommand implements Command {
    private final TaskService taskService;
    private final String name = "complete";

    public CompleteTaskCommand(TaskService taskService) {
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

        Optional<Integer> taskIdOpt = Util.safeParseInt(args[0]);
        if (taskIdOpt.isEmpty()) {
            System.out.println("Invalid task ID: " + args[0]);
            return;
        }
        int taskId = taskIdOpt.get();

        boolean success = taskService.completeTask(taskId);
        if (success) {
            System.out.println("Task " + taskId + " has been completed");
        }
        else {
            System.out.println("Task " + taskId + " not found");
        }
    }

    @Override
    public String help() {
        return "Usage: %s <task_id> -- Marks the task with the given ID as completed.".formatted(name);
    }
}
