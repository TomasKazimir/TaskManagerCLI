package repository;

import model.Task;

import java.util.*;

public class InMemTaskRepository implements TaskRepository {
    private final Map<Integer, Task> storage = new HashMap<>();

    @Override
    public Task save(Task task) {
        storage.put(task.getId(), task);
        return task;
    }

    @Override
    public Optional<Task> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean deleteById(int id) {
        return storage.remove(id) != null;
    }
}
