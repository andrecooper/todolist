package com.home.todoList.app.comments.model;

import com.home.todoList.app.tasks.model.Task;
import com.home.todoList.app.users.model.User;

import javax.persistence.Entity;
import java.sql.Date;


public class Comment {
    private long id;
    private User author;
    private Task task;
    private Date commentDate;
}
