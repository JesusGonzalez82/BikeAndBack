package com.jesus.bikeandride.model;

import java.util.Date;

public class Bike {

    // type
    // marca
    // modelo
    // anio
    // peso
    // material
    // status
    // id_usuario

    // Esto es un DTO y lo que traemos de la BBDD es un DAO

    private String type;
    private String bike_brand;
    private String model;
    private Date year;
    private int weight;
    private String bike_material;
    private String status;
    private int id;

    public String getType() {
        return type;
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

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
