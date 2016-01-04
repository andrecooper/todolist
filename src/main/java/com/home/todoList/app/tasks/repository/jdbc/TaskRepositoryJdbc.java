package com.home.todoList.app.tasks.repository.jdbc;

import com.home.todoList.app.tasks.model.Task;
import com.home.todoList.app.tasks.repository.TaskRepository;
import com.home.todoList.app.users.model.User;
import com.home.todoList.common.utilities.annotations.persistance.PersistanceSource;
import com.home.todoList.common.utilities.annotations.persistance.Source;

import java.util.List;
import java.util.Set;

@PersistanceSource(value = Source.JDBC)
public class TaskRepositoryJdbc implements TaskRepository {
    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public List<Task> getTasksByOwner(User username) {
        return null;
    }

    @Override
    public boolean addTask(Task task) {
        return false;
    }

    @Override
    public boolean deleteTask(long id) {
        return false;
    }
}
