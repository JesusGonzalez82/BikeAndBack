/*
package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.EmailDao;
import com.jesus.bikeandride.Model.EmailDdbb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final List<EmailDdbb> emails = new ArrayList<>();
    @Autowired
    private EmailDao emailDao;

    @GetMapping("/listaEmail")
    public List<EmailDdbb> getEmail(){
        return emails;
    }
}
*/
