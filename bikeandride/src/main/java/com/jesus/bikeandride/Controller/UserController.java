package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.EmailDao;
import com.jesus.bikeandride.Dao.UserDao;
import com.jesus.bikeandride.Model.EmailDdbb;
import com.jesus.bikeandride.Model.User;
import com.jesus.bikeandride.Model.UserDdbb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final List<UserDdbb> users = new ArrayList<>();
    @Autowired
    private UserDao userdao;
    @Autowired
    private EmailDao emailDao;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
    public ResponseEntity<?> createUser(@RequestBody Map<String, Object> requestData) {
        try {
            String email = (String) requestData.get("email");
            String name = (String) requestData.get("name");
            String password = (String) requestData.get("password");
            String birthdayStr = (String) requestData.get("birthday");
            String status = (String) requestData.get("status");

            if (emailDao.emailExists(email)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "El email ya está registrado"));
            }

            // Hashear la contraseña antes de guardar
            String hashedPassword = passwordEncoder.encode(password);
            System.out.println("Password original: " + password);
            System.out.println("Password hasheada: " + hashedPassword);

            UserDdbb newUser = new UserDdbb();
            newUser.setName(name);
            newUser.setPassword(hashedPassword);
            newUser.setBirthday(LocalDate.parse(birthdayStr));
            newUser.setStatus(status);

            UserDdbb createdUser = userdao.createUser(newUser);

            EmailDdbb newEmail = new EmailDdbb(email, createdUser);
            emailDao.createEmail(newEmail);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "idUser", createdUser.getIdUser(),
                            "name", createdUser.getName(),
                            "email", email,
                            "birthday", createdUser.getBirthday().toString(),
                            "status", createdUser.getStatus()
                    ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al crear el usuario: " + e.getMessage()));
        }
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Buscar usuario por email
        UserDdbb user = userdao.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Email o contraseña incorrectos"));
        }

        // Verificar contraseña hasheada
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Email o contraseña incorrectos"));
        }

        if (!"activo".equals(user.getStatus())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "Usuario inactivo"));
        }

        // Obtener el email del usuario
        EmailDdbb userEmail = emailDao.getEmailByUserId(user.getIdUser());

        // Devolver usuario con email
        return ResponseEntity.ok(Map.of(
                "idUser", user.getIdUser(),
                "name", user.getName(),
                "email", userEmail != null ? userEmail.getEmail() : "",
                "birthday", user.getBirthday().toString(),
                "status", user.getStatus()
        ));
    }
}
