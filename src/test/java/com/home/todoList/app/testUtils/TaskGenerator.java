package com.home.todoList.app.testUtils;

import com.home.todoList.app.tasks.model.Task;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by andrew on 08.01.16.
 */

public class TaskGenerator {
    public static List<Task> generate(int amount){
        List<Task> taskList= new LinkedList<>();
        for (int i=0;i<amount;i++){
            Task task = new Task();
            task.setId(i);
            task.setTaskName("do something");
            task.setOwner(UserGenerator.getRandomUser());
            task.setCreationDate(null);
            task.setExecutionDate(null);
            taskList.add(task);
        }
        return taskList;
    }
}
