package com.jesus.bikeandride.Model;

public class Email {
    //DTO

    private int id_email;
    private String email;
    private int id_usuario;

    // Constructores

    public Email(int id_email, String email, int id_usuario) {
        this.id_email = id_email;
        this.email = email;
        this.id_usuario = id_usuario;
    }

    // Getters y Setters


    public int getId_email() {
        return id_email;
    }

    public void setId_email(int id_email) {
        this.id_email = id_email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}