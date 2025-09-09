package com.jesus.bikeandride.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "emails")
public class EmailDdbb implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_email")
    private long idEmail;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_usuario", nullable = true) // ningún usuario sin email
    private UserDdbb user;

    // Constructores

    public EmailDdbb() {}

    public EmailDdbb(String email){
        this.email = email;
    }

    public EmailDdbb(String email, UserDdbb user){
        this.email = email;
        this.user = user;
    }

    // Getters y Setters


    public long getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(long idEmail) {
        this.idEmail = idEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDdbb getUser() {
        return user;
    }

    public void setUser(UserDdbb user) {
        this.user = user;
    }
}
