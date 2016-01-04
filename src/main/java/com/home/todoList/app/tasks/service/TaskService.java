package com.home.todoList.app.tasks.service;

import com.home.todoList.app.tasks.model.Task;
import com.home.todoList.app.tasks.model.TaskDto;
import com.home.todoList.app.tasks.repository.TaskRepository;
import com.home.todoList.app.users.model.User;
import com.home.todoList.app.users.repository.UserRepository;
import com.home.todoList.common.utilities.annotations.persistance.PersistanceSource;
import com.home.todoList.common.utilities.annotations.persistance.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    @PersistanceSource(value = Source.SPRING_DATA)
    TaskRepository taskRepository;

    public List<TaskDto> getAllTasks() {
        List<Task> allTasks = taskRepository.getAllTasks();
        List<TaskDto> taskDtoList = new LinkedList<>();
        for (Task task : allTasks) {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setTaskName(task.getTaskName());
            taskDto.setOwner(task.getOwner().getUsername());
            taskDtoList.add(taskDto);
        }

        return taskDtoList;
    }

    public List<Task> getTaskListByUser(String user) {
        User creator = userRepository.findByUsername(user);
        return taskRepository.getTasksByOwner(creator);
    }

    @Transactional
    public Set<String> findAllTaskOwners() {
        Iterable<Task> allCreator = taskRepository.getAllTasks();

        System.out.println("\n ALL TASKS: " + allCreator);
        Set<String> creatorSet = new HashSet<>();
        for (Task task : allCreator) {
            creatorSet.add(task.getOwner().getUsername());
        }
        return creatorSet;
    }

    public void addTask(TaskDto taskDto) {
        if (taskDto == null){
            // TODO : Throw superException here
            return;
        }
        Task task = new Task();

        String taskOwnerName = taskDto.getOwner();
        if (taskOwnerName == null || taskOwnerName.equals("")){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            taskOwnerName = auth.getName();
            System.out.println("accepted request with " + taskDto+ " from: " + taskOwnerName);
        }

        User owner = userRepository.findByUsername(taskOwnerName);
        task.setTaskName(taskDto.getTaskName());
        task.setOwner(owner);
        taskRepository.addTask(task);
        System.out.println("added task " + task.getTaskName());
    }
}
