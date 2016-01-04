package com.home.todoList.app.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TaskHomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("/home");
    }
}
