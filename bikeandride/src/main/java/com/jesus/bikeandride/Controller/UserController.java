package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.model.UserDdbb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users/listaUsers/")
    public List<UserDdbb> getUsers(){
        List<UserDdbb> list = new ArrayList<>();
        list.add(new UserDdbb());
        return list;
    }

}
