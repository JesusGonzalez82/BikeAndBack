package com.jesus.bikeandride.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "imagenes")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long idImagen;

    @Column(length = 255)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('usuario', 'bicicleta', 'ruta', 'actividad')")
    private TipoImagen tipo;

    @Column(name = "id_usuario")
    private long idUsuario;

    @Column(name = "id_bici")
    private long idBici;

    @Column(name = "id_ruta")
    private long idRuta;

    @Column(name = "id_actividad")
    private long idActividad;

    @Column(name="fecha_subida")
    private LocalDateTime fechaSubida;

    public Imagen() {
        this.fechaSubida = LocalDateTime.now();
    }

    public Long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Long idImagen) {
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

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdBici() {
        return idBici;
    }

    public void setIdBici(long idBici) {
        this.idBici = idBici;
    }

    public long getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(long idRuta) {
        this.idRuta = idRuta;
    }

    public long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(long idActividad) {
        this.idActividad = idActividad;
    }

    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}



