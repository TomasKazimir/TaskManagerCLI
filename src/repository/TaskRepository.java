package repository;

import model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    public Task save(Task task);

    Optional<Task> findById(String id);

    List<Task> findAll();

    boolean deleteById(int id);
}
