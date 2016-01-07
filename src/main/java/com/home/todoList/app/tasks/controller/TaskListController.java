package com.home.todoList.app.tasks.controller;

import com.home.todoList.app.tasks.model.TaskDto;
import com.home.todoList.app.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api")
public class TaskListController {

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @RequestMapping(value = "/task/{username}", method = RequestMethod.GET)
    public List<TaskDto> getTaskListByUser(@PathVariable String username) {
        System.out.println("\n FIND TASKS FOR " + username);
        return taskService.getTaskListByUser(username);
    }

    @RequestMapping(value = "/task/owners", method = RequestMethod.GET)
    public Set<String> getCreatorList() {
        Set<String> creatorList = taskService.findAllTaskOwners();
        System.out.println("\n FIND TASKS FOR " + creatorList);
        return creatorList;
    }

    @RequestMapping(value= "/task", method = RequestMethod.POST, consumes = "application/json")
    public void addTask(@RequestBody TaskDto task) {
        System.out.println("RECEIVED TASK" + task);
        taskService.addTask(task);
    }
}
