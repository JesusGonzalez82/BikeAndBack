package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.UserDao;
import com.jesus.bikeandride.Model.UserDdbb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        UserDdbb usuario = new UserDdbb();
        usuario.setIdUser(1L);
        when(userDao.createUser(usuario)).thenReturn(usuario);
        UserDdbb resultado = userController.createUser(usuario);
        assertEquals(1, resultado.getIdUser());

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
        UserDdbb usuario = new UserDdbb();
        usuario.setIdUser(1L);
        when(userDao.deactivateUser(1L)).thenReturn(usuario);
        UserDdbb resultado = userController.deactivateUser(1L);
        assertEquals(usuario.getIdUser(),resultado.getIdUser());
    }

    @Test
    void deactivateUserNull() {
        UserDdbb usuario = new UserDdbb();
        usuario.setIdUser(1L);
        when(userDao.deactivateUser(1L)).thenReturn(null);
        RuntimeException exception = assertThrows(RuntimeException.class, () ->{
            userController.deactivateUser(1L);
        });
        assertEquals("Error: Usuario no encontrado", exception.getMessage());
        verify(userDao, times(1)).deactivateUser(1L);
    }

    @Test
    void reactivateUser() {
        UserDdbb usuario = new UserDdbb();
        usuario.setIdUser(1L);
        when(userDao.reactivateUser(1L)).thenReturn(usuario);
        UserDdbb resultado = userController.reactivateUser(1L);
        assertEquals(usuario.getIdUser(),resultado.getIdUser());
    }

    @Test
    void reactivateUserNull() {
        UserDdbb usuario = new UserDdbb();
        usuario.setIdUser(1L);
        when(userDao.reactivateUser(1L)).thenReturn(null);
        RuntimeException exception = assertThrows(RuntimeException.class, () ->{
            userController.reactivateUser(1L);
        });
        assertEquals("Error: Usuario no encontrado", exception.getMessage());
        verify(userDao, times(1)).reactivateUser(1L);
    }


}