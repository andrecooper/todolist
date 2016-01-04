package com.home.todoList.app.users.controller;

import com.home.todoList.app.users.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "/loginme", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("/loginme", "user", new User());
    }
}
