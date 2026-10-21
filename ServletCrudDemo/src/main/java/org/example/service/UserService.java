package org.example.service;

import org.example.entity.User;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    private Map<Integer, User> userDB;

    public UserService() {
        userDB = new HashMap<>();
    }

    public User createUser (User userRequest){
        userDB.put(userRequest.getId(), userRequest);
        return userRequest;
    }


    public List<User> getAllUser(){
        List<User> userResponse = new ArrayList<>();

        for(User user: userDB.values()){
            userResponse.add(user);
        }

        return userResponse;
    }

    public User getUserById(Integer id){
        return userDB.getOrDefault(id, null);
    }

    public String deleteById(Integer id) {
        userDB.remove(id);
        return "User deleted";
    }
}
