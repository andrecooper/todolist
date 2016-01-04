package com.home.todoList.app.users.controller;

import com.home.todoList.app.users.model.User;
import com.home.todoList.app.users.service.UserService;
import com.home.todoList.common.utilities.exceptions.SuperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class UserController {

    /*@Autowired
    private SessionRegistry sessionRegistry;*/

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", produces = "application/json")
    public
    @ResponseBody
    List<User> getOnlineUsers() {
        /* final ? */
        /*List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        List<String> usersNamesList = new ArrayList<>();

        System.out.println("I AM HERE!!! MF1" + sessionRegistry);
        System.out.println("I AM HERE!!! MF2" + allPrincipals);
        for (Object principal: allPrincipals) {
            System.out.println("FOUND PRICIPAL: " + principal);
            if (principal instanceof User) {
                usersNamesList.add(((User) principal).getUsername());
            }
        }*/

        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        System.out.println("Registering user: " + user);

        ModelAndView mv = new ModelAndView();
        try {
            userService.registerUser(user);
        } catch (SuperException e) {
            mv.addObject("errorMessage", "User '" + user.getUsername() + "' already exists");
            mv.setViewName("/loginme?error");
            System.out.println("USER " + user.getUsername() + " already exists");
        }

        mv.setViewName("/loginme");
        mv.addObject("message", "User '" + user.getUsername() + "' created successfully");

        return mv;
    }
}
