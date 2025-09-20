package com.jesus.bikeandride.Dao;

import com.jesus.bikeandride.Model.ImagenDdbb;
import com.jesus.bikeandride.Model.TipoImagen;

import java.util.List;
import java.util.Optional;

public interface IImagenDao {

    // Métodos CRUD básicos
    ImagenDdbb save(ImagenDdbb imagen);
    Optional<ImagenDdbb> findById(Integer id);
    List<ImagenDdbb> findAll();
    void deleteById(Integer id);
    boolean existsById(Integer id);
    long count();

    // Métodos de búsqueda específicos
    Optional<ImagenDdbb> findByIdUsuarioAndTipo(Integer idUsuario, TipoImagen tipo);
    List<ImagenDdbb> findByIdUsuario(Integer idUsuario);
    List<ImagenDdbb> findByIdBici(Integer idBici);
    List<ImagenDdbb> findByIdRuta(Integer idRuta);
    List<ImagenDdbb> findByIdActividad(Integer idActividad);
    List<ImagenDdbb> findByTipo(TipoImagen tipo);

    // Métodos adicionales útiles
    Long countByIdUsuario(Integer idUsuario);
    Boolean existsByIdBiciAndTipo(Integer idBici, TipoImagen tipo);
    void deleteByIdUsuario(Integer idUsuario);
    Optional<ImagenDdbb> findFirstImagenByIdBici(Integer idBici);
}