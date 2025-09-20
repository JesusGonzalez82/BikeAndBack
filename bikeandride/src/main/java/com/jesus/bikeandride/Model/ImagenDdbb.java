package com.jesus.bikeandride.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "imagenes")
public class ImagenDdbb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Integer idImagen;

    @Column(length = 255)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('usuario', 'bicicleta', 'ruta', 'actividad')")
    private TipoImagen tipo;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_bici")
    private Integer idBici;

    @Column(name = "id_ruta")
    private Integer idRuta;

    @Column(name = "id_actividad")
    private Integer idActividad;

    @Column(name="fecha_subida")
    private LocalDateTime fechaSubida;

    public ImagenDdbb() {
        this.fechaSubida = LocalDateTime.now();
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TipoImagen getTipo() {
        return tipo;
    }

    public void setTipo(TipoImagen tipo) {
        this.tipo = tipo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdBici() {
        return idBici;
    }

    public void setIdBici(Integer idBici) {
        this.idBici = idBici;
    }

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}



