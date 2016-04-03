package com.home.todoList.app.testUtils;

import com.home.todoList.app.users.model.User;

import java.util.*;


public class UserGenerator {

    private static List<String> userNameList = new ArrayList(Arrays.asList("andrew", "eddie", "tony", "jimmy", "stuart", "joseph", "lemmy", "ozzy", "lars", "nick"));
    private static List<User> userList = new LinkedList<>();

    public static List<User> generate() {
        if (userList.size()>0){
            return userList;
        }

        for (int i = 0; i < userNameList.size(); i++) {
            User user = new User();
            user.setId(i);
            String username = userNameList.get(i);
            user.setUsername(username);
            user.setEnabled(true);
            user.setPassword("superPassword"+username+i);
            userList.add(user);
        }
        return userList;
    }


    public static User getRandomUser(){
        generate();
        Random random = new Random();
        int index = random.nextInt(userNameList.size());
        return userList.get(index);
    }

    public static User getUserByName(String username){
        generate();
        for (User user : userList) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        for (int i=0; i<20; i++){
            System.out.println(getRandomUser());
        }
    }
}



