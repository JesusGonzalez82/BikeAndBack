package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.UserDao;
import com.jesus.bikeandride.Model.UserDdbb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

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
//        userDao = new UserDao();
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
    }

    @Test
    void partialUpdateUser() {
    }

    @Test
    void deactivateUser() {
    }

    @Test
    void reactivateUser() {
    }
}