package com.home.todoList.app.tasks.repository.spring;

import com.home.todoList.app.tasks.model.Task;
import com.home.todoList.app.tasks.repository.TaskRepository;
import com.home.todoList.app.users.model.User;
import com.home.todoList.common.utilities.annotations.persistance.PersistanceSource;
import com.home.todoList.common.utilities.annotations.persistance.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@PersistanceSource(value = Source.SPRING_DATA)
public class TaskRepositorySpringImpl implements TaskRepository {

    @Autowired
    TaskRepositorySpringData repository;

    @Override
    public List<Task> getAllTasks() {
        System.out.println("\n WHOOOHOO!!!! I'M USING SPRING DATA !!!");
        return (List<Task>) repository.findAll();
    }

    @Override
    public List<Task> getTasksByOwner(User username) {
        List<Task> byOwner = repository.findByOwner(username);
        System.out.println("FOUND TASKS: " + byOwner);
        return byOwner;
    }

    @Override
    public boolean addTask(Task task) {
        Task saveResult = repository.save(task);
        return true;
    }

    @Override
    public boolean deleteTask(long id) {
        repository.delete(id);
        return true;
    }
}
