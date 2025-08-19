package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.model.BikeDdbb;
import com.jesus.bikeandride.model.UserDdbb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final List<UserDdbb> users = new ArrayList<>();
    private final List<BikeDdbb> bikes = new ArrayList<>();

    @GetMapping("/users/listaUsers/")
    public List<UserDdbb> getUsers(){
        return users;
    }

    @GetMapping("/bikes/listaBikes/")
    public List<BikeDdbb> getAllBikes(){
        return bikes;
    }

    @PostMapping("/users/")
    public UserDdbb addUser(@RequestBody UserDdbb newUser){
        // Asignamos ID automático
        int newId = users.size() + 1;
        newUser.setId(newId);
        users.add(newUser);
        return newUser;
    }

    @PostMapping("/bikes/")
    public BikeDdbb addBike(@RequestBody BikeDdbb newBike){
        int newId = bikes.size() + 1;
        newBike.setId(newId);
        bikes.add(newBike);
        return newBike;
    }
}
