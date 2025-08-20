package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.BikeDao;
import com.jesus.bikeandride.model.BikeDdbb;
import com.jesus.bikeandride.model.UserDdbb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final List<UserDdbb> users = new ArrayList<>();
    private final List<BikeDdbb> bikes = new ArrayList<>();
    @Autowired
    private BikeDao bikedao;

    @GetMapping("/users/listaUsers/")
    public List<UserDdbb> getUsers(){
        return users;
    }

    @GetMapping("/bikes/listaBikes/")
    public List<BikeDdbb> getAllBikes(){
        return bikes;
    }

    @GetMapping("/bikes/getListBikeByUserId/{id}")
    public List<BikeDdbb> getListBikeByUserId(@PathVariable Long id) {
        return bikedao.getListBikeByUserId(id);
    }


    @PostMapping("/users/")
    public UserDdbb addUser(@RequestBody UserDdbb newUser){
        // Asignamos ID automático
        int newId = users.size() + 1;
        newUser.setIdUser(newId);
        users.add(newUser);
        return newUser;
    }

    @PostMapping("/bikes/")
    public BikeDdbb addBike(@RequestBody BikeDdbb newBike){
        int newId = bikes.size() + 1;
        newBike.setIdBike(newId);
        bikes.add(newBike);
        return newBike;
    }
}
