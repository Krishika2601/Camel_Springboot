package com.camel.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final String EXTERNAL_API_URL = "https://reqres.in/api/users?page=2";

    @Autowired
    private RestTemplate restTemplate;

  
    @GetMapping("/all")
    public List<User> getAllUsers() {
        // Call the external API and fetch the list of users
        UserResponse response = restTemplate.getForObject(EXTERNAL_API_URL, UserResponse.class);
        if (response != null) {
            return response.getData();
        } else {
          
            return Arrays.asList();
        }
    }

  
    @GetMapping("/filter")
    public List<User> filterUsersByIds(@RequestParam List<Integer> ids) {
      
        UserResponse response = restTemplate.getForObject(EXTERNAL_API_URL, UserResponse.class);
        if (response != null) {
         
            List<User> filteredUsers = response.filterUsersByIds(ids);
            return filteredUsers;
        } else {
          
            return Arrays.asList();
        }
    }
}
