package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.UserDao;
import com.jesus.bikeandride.Model.UserDdbb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final List<UserDdbb> users = new ArrayList<>();
    @Autowired
    private UserDao userdao;

    @GetMapping("/users/listaUsers/")
    public List<UserDdbb> getUsers(){
        return users;
    }

    @GetMapping("/users/getListUserByUserId/{id}")
    public UserDdbb getUserById(@PathVariable long id){
        return userdao.getUserById(id);
    }

    @PostMapping("/users/")
    public UserDdbb addUser(@RequestBody UserDdbb newUser){
        // Asignamos ID automático
        int newId = users.size() + 1;
        newUser.setIdUser(newId);
        users.add(newUser);
        return newUser;
    }

}
