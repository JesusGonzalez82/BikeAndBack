package com.jesus.bikeandride.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name= "emails")
public class EmailDdbb implements Serializable {

    // id
    // email
    // id_usuario

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_email")
    private int idEmail;
    @Column(name = "email")
    private String email;



}
