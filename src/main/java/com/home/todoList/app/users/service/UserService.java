package com.home.todoList.app.users.service;

import com.home.todoList.app.users.model.Role;
import com.home.todoList.app.users.model.User;
import com.home.todoList.app.users.repository.UserRepository;
import com.home.todoList.common.utilities.exceptions.SuperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findByIsEnabled(true);
    }

    public void registerUser(User user) throws SuperException {
        if (userRepository.findByUsername(user.getUsername()) != null){
            throw new SuperException("User already exists");
        }
        user.setEnabled(true);
        Set<Role> role = new HashSet<>();
        role.add(Role.ROLE_USER);
        user.setRole(role);
        userRepository.save(user);
    }
}