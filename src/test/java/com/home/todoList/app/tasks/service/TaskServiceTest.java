package com.home.todoList.app.tasks.service;

import com.home.todoList.app.tasks.model.Task;
import com.home.todoList.app.tasks.model.TaskDto;
import com.home.todoList.app.tasks.repository.TaskRepository;
import com.home.todoList.app.testUtils.TaskGenerator;
import com.home.todoList.app.testUtils.UserGenerator;
import com.home.todoList.app.users.model.User;
import com.home.todoList.app.users.repository.UserRepository;
import com.home.todoList.common.utilities.annotations.persistance.PersistanceSource;
import com.home.todoList.common.utilities.annotations.persistance.Source;
import com.home.todoList.common.utilities.convertors.TaskToDtoConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TaskServiceTest {

    @Configuration
    static class TaskServiceTestConfiguration {

        @Bean
        @PersistanceSource(value = Source.JDBC)
        public TaskRepository taskRepository() {
            return Mockito.mock(TaskRepository.class);
        }

        @Bean

        public UserRepository userRepository(){
            return Mockito.mock(UserRepository.class);
        }

        @Bean
        public TaskService taskService(){
            return new TaskService();
        }

        @Bean
        public TaskToDtoConverter taskToDtoConverter() {
            return new TaskToDtoConverter();
        }

    }

    @Autowired
    private TaskService taskService;

    @Autowired
    @PersistanceSource(value = Source.JDBC)
    private TaskRepository taskRepository;

    @Autowired
    TaskToDtoConverter taskToDtoConverter;

    @Autowired
    UserRepository userRepository;

    final static int TASKLIST_SIZE = 20;

    @Before
    public void setup(){
        List<Task> taskList = TaskGenerator.generate(TASKLIST_SIZE);
        List<Task> andrewsTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getOwner().getUsername().equals("andrew")){
                andrewsTasks.add(task);
            }
        }
        User andrew = UserGenerator.getUserByName("andrew");

        Mockito.when(taskRepository.getAllTasks()).thenReturn(taskList);
        Mockito.when(taskRepository.getTasksByOwner(andrew)).thenReturn(andrewsTasks);
        Mockito.when(userRepository.findByUsername(Matchers.anyString())).thenReturn(UserGenerator.getUserByName(Matchers.anyString()));
//        Mockito.when(userRepository.findByIsEnabled(Matchers.anyBoolean())).thenReturn(UserGenerator.generate());

    }

    @Test
    public void testGetAllTasks() throws Exception {
        List<TaskDto> allTasks = taskService.getAllTasks();
        assertEquals("Check for all users",TASKLIST_SIZE,allTasks.size());
        assertTrue("Return type TaskDto", allTasks.get(1) instanceof TaskDto);
    }


    @Test
//    TODO : BAD TEST
    public void testGetTaskListByExistingUser() throws Exception {
        User andrew = UserGenerator.getUserByName("andrew");
        assertNotNull("Check existing user tasks 1",taskService.getTaskListByUser("andrew"));
        assertTrue("Check existing user tasks 2",taskService.getTaskListByUser("andrew").size() >0);
    }

    @Test
    public void testGetTaskListByNonExistingUser() throws Exception {
        User andrew = UserGenerator.getUserByName("tolik");
        assertTrue("Check non existing user tasks", taskService.getTaskListByUser("andrew").isEmpty());

    }

//    @Test
    public void testFindAllTaskOwners() throws Exception {

    }

    @Test
    public void testAddTask() throws Exception {
        System.out.println("OLOLO");
    }
}