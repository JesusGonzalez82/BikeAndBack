package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.EmailDao;
import com.jesus.bikeandride.Model.EmailDdbb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test unitarios del controlador de Emails")
public class EmailControllerTest {

    @InjectMocks
    private EmailController emailController;

    @Mock
    private EmailDao emailDao;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        emailController = new EmailController(this.emailDao);
    }

    @Test
    @DisplayName("Creamos un nuevo email")
    void createEmail(){

    }

}
