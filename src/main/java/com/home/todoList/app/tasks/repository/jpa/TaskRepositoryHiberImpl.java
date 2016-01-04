package com.home.todoList.app.tasks.repository.jpa;

import com.home.todoList.app.tasks.model.Task;
import com.home.todoList.app.tasks.repository.TaskRepository;
import com.home.todoList.app.users.model.User;
import com.home.todoList.common.utilities.annotations.persistance.PersistanceSource;
import com.home.todoList.common.utilities.annotations.persistance.Source;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;


@Repository
@PersistanceSource(value = Source.JPA)
public class TaskRepositoryHiberImpl implements TaskRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Task> getAllTasks() {
        System.out.println("\n WHOOOHOO!!!! I'M USING JPA !!!");
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
/*        CriteriaQuery<Task> query = cb.createQuery(Task.class);
        Root<Task> taskRoot = query.from(Task.class);
        query.select(taskRoot);
        return entityManager.createQuery(query).getResultList();*/
        return null;
    }

    @Override
    public List<Task> getTasksByOwner(User username) {
        return null;
    }

    @Override
    public boolean addTask(Task task) {
        entityManager.persist(task);
        return true;
    }

    @Override
    public boolean deleteTask(long id) {
        return false;
    }
}
