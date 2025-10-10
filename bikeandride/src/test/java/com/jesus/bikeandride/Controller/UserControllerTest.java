package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.UserDao;
import com.jesus.bikeandride.Model.UserDdbb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserDao userDao;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        userController = new UserController(this.userDao);
    }

    @Test
    void getUserById() {
        UserDdbb usuario = new UserDdbb();
        usuario.setIdUser(1L);
        when(userDao.getUserById(1L)).thenReturn(usuario);
        UserDdbb resultado = userController.getUserById(1L);
        assertEquals(1, resultado.getIdUser());
    }

    @Test
    void createUser() {
<<<<<<< Updated upstream
=======
        UserDdbb usuario = new UserDdbb();
        usuario.setIdUser(1L);

        when(userDao.createUser(usuario)).thenReturn(usuario);

        UserDdbb resultado = userDao.createUser(usuario);
>>>>>>> Stashed changes
    }


        @Test
    void partialUpdateUser() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Cristian");
        updates.put("password", "Cristian");
        updates.put("birthday", "1987-07-17");
        updates.put("status", "Enabled");
        UserDdbb usuario = new UserDdbb();
        usuario.setIdUser(1L);
        when(userDao.getUserById(1L)).thenReturn(usuario);
        when(userDao.updateUser(usuario)).thenReturn(usuario);
        UserDdbb resultado = userController.partialUpdateUser(1L,updates);
        assertEquals(1L,resultado.getIdUser());
    }


    @Test
    void partialUpdateUserLocalDate() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Cristian");
        updates.put("password", "Cristian");
        updates.put("birthday", LocalDate.now());
        updates.put("status", "Enabled");
        UserDdbb usuario = new UserDdbb();
        usuario.setIdUser(1L);
        when(userDao.getUserById(1L)).thenReturn(usuario);
        when(userDao.updateUser(usuario)).thenReturn(usuario);
        UserDdbb resultado = userController.partialUpdateUser(1L,updates);
        assertEquals(1L,resultado.getIdUser());
    }

    @Test
    void partialUpdateUserError() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Cristian");
        updates.put("password", "Cristian");
        updates.put("birthday", LocalDate.now());
        updates.put("status", "Enabled");
        UserDdbb usuario = new UserDdbb();
        usuario.setIdUser(1L);
        when(userDao.getUserById(1L)).thenReturn(usuario);
        when(userDao.updateUser(usuario)).thenReturn(usuario);
        UserDdbb resultado = userController.partialUpdateUser(1L,updates);
        assertEquals(1L,resultado.getIdUser());
    }

    @Test
    void deactivateUser() {
    }

    @Test
    void reactivateUser() {
    }
}