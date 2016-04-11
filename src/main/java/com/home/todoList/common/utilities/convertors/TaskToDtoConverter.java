package com.home.todoList.common.utilities.convertors;

import com.home.todoList.app.tasks.model.Task;
import com.home.todoList.app.tasks.model.TaskDto;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TaskToDtoConverter {

    public TaskDto getTaskDTO(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTaskName(task.getTaskName());
        taskDto.setOwner(task.getOwner().getUsername());
        return taskDto;
    }

    public List<TaskDto> getTaskDTO(List<Task> taskList) {
        List<TaskDto> taskDtoList = new LinkedList<>();
        for (Task task : taskList) {
            TaskDto taskDto = getTaskDTO(task);
            taskDtoList.add(taskDto);
        }
        return taskDtoList;
    }

}
