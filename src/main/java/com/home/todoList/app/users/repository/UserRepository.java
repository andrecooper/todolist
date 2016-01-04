package com.home.todoList.app.users.repository;

import com.home.todoList.app.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
    public List<User> findByIsEnabled(boolean isEnabled);

}
