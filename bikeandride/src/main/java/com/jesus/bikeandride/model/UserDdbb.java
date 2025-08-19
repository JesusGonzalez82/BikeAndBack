package com.jesus.bikeandride.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name= "usuarios")
public class UserDdbb implements Serializable {

    // name
    // password
    // id
    // email
    // birthday

    // Esto es un DTO y lo que traemos de la BBDD es un DAO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_usuario")
    private int id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "fecha_nac")
    private String birthday;
    @Column(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
