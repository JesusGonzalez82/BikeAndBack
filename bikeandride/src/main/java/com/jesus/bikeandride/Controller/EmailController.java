package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.EmailDao;
import com.jesus.bikeandride.Model.Email;
import com.jesus.bikeandride.Model.EmailDdbb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailDao emailDao;

    @GetMapping("/exists/id/{id}")
    public boolean emailExistsById(@PathVariable long id) {
        return emailDao.emailExistsById(id);
    }

    @GetMapping("/exists/email/{email}")
    public boolean emailExistsByAddress(@PathVariable String email) {
        return emailDao.emailExistsByAddress(email);
    }

    @GetMapping("/user/{userId}/hasEmails")
    public boolean userHasEmail(@PathVariable long userId) {
        return emailDao.userHasEmails(userId);
    }

    @GetMapping("/belongs/{emails}/users/{userId}")
    public boolean emailBelongsToUser(@PathVariable long emailId, @PathVariable long userId) {
        return emailDao.emailBelongsToUser(emailId, userId);
    }

    @PostMapping("/create")
    public boolean createEmail(@RequestBody EmailDdbb newEmail) {
        return emailDao.createEmail(newEmail);
    }

    @PostMapping("/update")
    public boolean updateEmail(@RequestBody EmailDdbb email) {
        return emailDao.updateEmail(email);
    }

    @DeleteMapping("/delete/id/{id}")
    public boolean deleteEmailById(@PathVariable long id) {
        return emailDao.deleteEmailById(id);
    }

    @DeleteMapping("/delete/email/{email}")
    public boolean deleteEmailByAddress(@PathVariable String Email) {
        return emailDao.deleteEmailByAddress(email);
    }

    //     Mantenemos el método que devuelve la lista para los casos en los que tenemos que comprobar los datos
    @GetMapping("/user/{userId}/list")
    public List<EmailDdbb> getEmailByUser(@PathVariable long userId) {
        return emailDao.getListEmailByUserId(userId);
    }

}
