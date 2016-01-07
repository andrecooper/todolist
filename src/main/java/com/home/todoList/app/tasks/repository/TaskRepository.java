package com.home.todoList.app.tasks.repository;

import com.home.todoList.app.tasks.model.Task;
import com.home.todoList.app.users.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskRepository {
    List<Task> getAllTasks();
    List<Task> getTasksByOwner(User user);
    boolean addTask (Task task);
    boolean deleteTask (long id);
}
