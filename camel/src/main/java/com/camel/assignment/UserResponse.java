package com.camel.assignment;


import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {
    private List<User> data;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    
    public List<User> filterUsersByIds(List<Integer> ids) {
        return data.stream()
                .filter(user -> ids.contains(user.getId()))
                .collect(Collectors.toList());
    }
}
