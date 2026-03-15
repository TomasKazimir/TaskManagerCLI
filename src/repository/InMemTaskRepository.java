package repository;

import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemTaskRepository implements TaskRepository {
    private List<Task> tasks;
    public InMemTaskRepository() {
        tasks = new  ArrayList<>();
    }

    @Override
    public Task save(Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public Optional<Task> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAll() {
        return List.of();
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
