package mephi.java.exam.service;

import mephi.java.exam.model.User;

import java.util.*;

public class UserService {
    private final Map<String, User> userMap = new HashMap<>();
    public String createUser(){
        String uuid = UUID.randomUUID().toString();
        User user = new User(uuid);
        userMap.put(uuid, user);
        return uuid;
    }
    public User getUserByUuid(String uuid){
        return userMap.get(uuid);
    }
}
