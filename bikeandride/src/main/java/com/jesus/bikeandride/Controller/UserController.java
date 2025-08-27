package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.UserDao;
import com.jesus.bikeandride.Model.UserDdbb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<UserDdbb> users = new ArrayList<>();
    @Autowired
    private UserDao userdao;

    public UserController(UserDao userdao) {
        this.userdao = userdao;
    }

    /*    @GetMapping("/listaUsers/")
    public List<UserDdbb> getUsers(){
        return users;
    }*/

    @GetMapping("/getListUserByUserId/{id}")
    public UserDdbb getUserById(@PathVariable Long id){
        return userdao.getUserById(id);
    }
/*
    @PostMapping("/users/")
    public UserDdbb addUser(@RequestBody UserDdbb newUser){
        // Asignamos ID automático
        int newId = users.size() + 1;
        newUser.setIdUser(newId);
        users.add(newUser);
        return newUser;
    }

 */

    @PostMapping("/create")
    public UserDdbb createUser(@RequestBody UserDdbb newUser){
        return userdao.createUser(newUser);
    }

    @PatchMapping("/update/{id}")
    public UserDdbb partialUpdateUser(@PathVariable long id, @RequestBody Map<String, Object> updates){
        try{
            UserDdbb existingUser = userdao.getUserById(id);
            if (existingUser == null){
                throw new RuntimeException("Usuario no encontrado");
            }

            updates.forEach((key, value) -> {
                switch (key){
                    case "name":
                        existingUser.setName((String) value);
                        break;
                    case "password":
                        existingUser.setPassword((String) value);
                        break;
                    case "birthday":
                        if (value instanceof String){
                            existingUser.setBirthday(LocalDate.parse((String) value));
                        }else if (value instanceof LocalDate){
                            existingUser.setBirthday((LocalDate) value);
                        }
                        break;
                    case "status":
                        existingUser.setStatus((String) value);
                        break;
                }
            });
            return userdao.updateUser(existingUser);
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public UserDdbb deactivateUser(@PathVariable long id){
        UserDdbb user = userdao.deactivateUser(id);
        if (user == null){
            throw new RuntimeException("Error: Usuario no encontrado");
        }
        return user;
    }

    @PutMapping("/{id}/reactivate")
    public UserDdbb reactivateUser(@PathVariable long id){
        UserDdbb user = userdao.reactivateUser(id);
        if (user == null){
            throw new RuntimeException("Error: Usuario no encontrado");
        }
        return user;
    }

}
