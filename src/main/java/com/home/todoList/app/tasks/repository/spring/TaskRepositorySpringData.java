package com.home.todoList.app.tasks.repository.spring;

import com.home.todoList.app.tasks.model.Task;
import com.home.todoList.app.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by andrew on 22.08.15.
 */

@Repository
public interface TaskRepositorySpringData extends CrudRepository<Task, Long> {
    List<Task> findByOwner(User owner);

    /*List<Task> findTaskByOwner(User owner);
    List<Task> findTaskByOwnerId(Integer id);*/

}
