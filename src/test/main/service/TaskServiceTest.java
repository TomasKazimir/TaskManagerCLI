package main.service;

import model.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.TaskRepository;
import service.TaskService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    private TaskService taskService;
    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        taskService = new TaskService(taskRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void createTask_savesCorrectTask() {
        // Arrange
        String desc = "Test task";

        when(taskRepository.save(any(Task.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Task result = taskService.createTask(desc);

        // Assert
        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).save(captor.capture());

        Task saved = captor.getValue();

        assertEquals(desc, saved.getDescription());
        assertFalse(saved.isCompleted());
        assertEquals(1, saved.getId());

        assertEquals(saved, result);
    }

    @Test
    void createTask_allowsEmptyDescription() {
        // Arrange
        when(taskRepository.save(any(Task.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));
        // Act
        Task result = taskService.createTask("");
        // Assert
        assertEquals("", result.getDescription());
    }

    @Test
    void listTasks_returnsAllTasks() {
        // Arrange
        List<Task> tasks = Arrays.asList(
            new Task(1, "TaskA"),
            new Task(2, "TaskB")
        );
        when(taskRepository.findAll()).thenReturn(tasks);
        // Act
        List<Task> result = taskService.listTasks();
        // Assert
        assertEquals(2, result.size());
        assertEquals("TaskA", result.get(0).getDescription());
        assertEquals("TaskB", result.get(1).getDescription());
    }

    @Test
    void listTasks_emptyList() {
        // Arrange
        when(taskRepository.findAll()).thenReturn(Collections.emptyList());
        // Act
        List<Task> result = taskService.listTasks();
        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void completeTask_success_marksAndSaves() {
        // Arrange
        Task task = new Task(1, "ToBeCompleted");

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        // Act
        boolean result = taskService.completeTask(1);

        // Assert
        assertTrue(result);
        assertTrue(task.isCompleted());

        verify(taskRepository).save(task);
    }

    @Test
    void completeTask_alreadyCompleted_stillSaves() {
        // Arrange
        Task task = new Task(1, "Already done");
        task.markCompleted();

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        // Act
        boolean result = taskService.completeTask(1);
        // Assert
        assertTrue(result);
        verify(taskRepository).save(task);
    }

    @Test
    void completeTask_notFound() {
        // Arrange
        when(taskRepository.findById(99)).thenReturn(Optional.empty());
        // Act
        boolean result = taskService.completeTask(99);
        // Assert
        assertFalse(result);
        verify(taskRepository, never()).save(any());
    }

    @Test
    void deleteTask_success() {
        // Arrange
        when(taskRepository.deleteById(1)).thenReturn(true);
        // Act
        boolean result = taskService.deleteTask(1);
        // Assert
        assertTrue(result);
        verify(taskRepository).deleteById(1);
    }

    @Test
    void deleteTask_notFound() {
        // Arrange
        when(taskRepository.deleteById(42)).thenReturn(false);
        // Act
        boolean result = taskService.deleteTask(42);
        // Assert
        assertFalse(result);
        verify(taskRepository).deleteById(42);
    }
}
