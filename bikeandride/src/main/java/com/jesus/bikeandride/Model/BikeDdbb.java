package com.jesus.bikeandride.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;

@Entity
@Table(name= "bicicletas")
public class BikeDdbb implements Serializable {

    // tipo bici
    // marca
    // modelo
    // anio
    // peso
    // material
    // status
    // id_usuario

    // Esto es un DTO y lo que traemos de la BBDD es un DAO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_bici")
    private long idBike;
    @Column(name = "tipo_bici")
    private String type;
    @Column(name = "marca")
    private String bike_brand;
    @Column(name = "modelo")
    private String model;
    @Column(name = "anio")
    private Year birthday;
    @Column(name = "peso")
    private int weight;
    @Column(name = "material")
    private String bike_material;
    @Column(name = "status")
    private String status;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn (name = "id_usuario", nullable = false)
    private UserDdbb user;

    public long getIdBike() {
        return idBike;
    }

    public void setIdBike(long idBike) {
        this.idBike = idBike;
    }

    public String getType() {
        return type;
    }

    public UserDdbb getUser() {
        return user;
    }

    public void setUser(UserDdbb user) {
        this.user = user;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBike_brand() {
        return bike_brand;
    }

    public void setBike_brand(String bike_brand) {
        this.bike_brand = bike_brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Year getBirthday() {
        return birthday;
    }

    public void setBirthday(Year birthday) {
        this.birthday = birthday;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBike_material() {
        return bike_material;
    }

    public void setBike_material(String bike_material) {
        this.bike_material = bike_material;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
