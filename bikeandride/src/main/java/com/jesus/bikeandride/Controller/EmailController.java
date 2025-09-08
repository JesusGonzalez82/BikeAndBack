package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.EmailDao;
import com.jesus.bikeandride.Model.EmailDdbb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {

        private final List<EmailDdbb> emails = new ArrayList<>();
        @Autowired
        private EmailDao emailDao;

        @GetMapping("/listaEmails/")
        public List<EmailDdbb> getEmails(){
            return  emails;
        }

        @GetMapping("/getListaEmailByUserId/{id}")
        public List<EmailDdbb> getListEmailByUserId(@PathVariable long id){
            return emailDao.getListEmailByUserId(id);
        }

}
