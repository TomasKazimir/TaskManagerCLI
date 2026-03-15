package service;

import cli.CommandParser;
import model.Task;
import repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskService {
    private final TaskRepository repository;
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(String description) {
        int id = idGenerator.getAndIncrement();
        Task task = new Task(id, description);
        return repository.save(task);
    }

    public List<Task> listTasks() {
        return repository.findAll();
    }

    public boolean completeTask(int id) {
        Optional<Task> taskOptional = repository.findById(id);

        if (taskOptional.isEmpty()) {
            return false;
        }

        Task taskToComplete = taskOptional.get();
        taskToComplete.markCompleted();
        repository.save(taskToComplete);
        return true;
    }

    public boolean deleteTask(int id) {
        return repository.deleteById(id);
    }


}
