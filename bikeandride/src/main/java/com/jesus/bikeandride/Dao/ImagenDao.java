package com.jesus.bikeandride.Dao;

import com.jesus.bikeandride.Model.ImagenDdbb;
import com.jesus.bikeandride.Model.TipoImagen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Repository
public class ImagenDao implements IImagenDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    public ImagenDdbb save (ImagenDdbb imagen){
        if (imagen.getIdImagen() == null){
            entityManager.persist(imagen);
            return imagen;
        }else{
            return entityManager.merge(imagen);
        }
    }

    @Override
    public Optional<ImagenDdbb> findById(Integer id){
        ImagenDdbb imagen = entityManager.find(ImagenDdbb.class, id);
        return Optional.ofNullable(imagen);
    }

    @Override
    public List<ImagenDdbb> findAll(){
        TypedQuery<ImagenDdbb> query = entityManager.createQuery(
                "SELECT i FROM ImagenDdbb i ORDER BY i.fechasubida DESC", ImagenDdbb.class);
            return  query.getResultList();
    }

    @Override
    public void deleteById(Integer id){
        ImagenDdbb imagen = entityManager.find(ImagenDdbb.class, id);
        if (imagen != null){
            entityManager.remove(imagen);
        }
    }

    @Override
    public boolean existsById(Integer id){
        return findById(id).isPresent();
    }

    @Override
    public long count(){
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(i) FROM ImagenDdbb i", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Optional<ImagenDdbb> findByIdUsuarioAndTipo(Integer idUsuario, TipoImagen tipo){
        try {
            TypedQuery<ImagenDdbb> query = entityManager.createQuery(
                    "SELECT i FROM ImagenDdbb i WHERE i.idUsuario = :idUsuario AND i.tipo = :tipo",
                    ImagenDdbb.class);
            query.setParameter("idUsuario", idUsuario);
            query.setParameter("tipo", tipo);
            return Optional.of(query.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public List<ImagenDdbb> findByIdUsuario(Integer idUsuario){
        TypedQuery<ImagenDdbb> query = entityManager.createQuery(
                "SELECT i FROM  ImagenDdbb i WHERE i.idUsuario = :idUsuario ORDER BY i.fechaSubida DESC",
                ImagenDdbb.class);
        query.setParameter("idUsuario", idUsuario);
        return  query.getResultList();
    }

    @Override
    public List<ImagenDdbb> findByIdBici(Integer idBici){
        TypedQuery<ImagenDdbb> query = entityManager.createQuery(
                "SELECT i FROM ImagenDdbb i WHERE i.idBici = :idBici ORDER BY i.fechaSubida DESC",
                ImagenDdbb.class);
        query.setParameter("idBici", idBici);
        return query.getResultList();
    }

    @Override
    public List<ImagenDdbb> findByIdRuta(Integer idRuta){
        TypedQuery<ImagenDdbb> query = entityManager.createQuery(
                "SELECT i FROM ImagenDdbb i WHERE i.idRuta = :idRuta ORDER BY i.fechaSubida DESC",
                ImagenDdbb.class);
        query.setParameter("idRuta", idRuta);
        return query.getResultList();
    }

    @Override
    public List<ImagenDdbb> findByIdActividad(Integer idActividad){
        TypedQuery<ImagenDdbb> query = entityManager.createQuery(
                "SELECT i FROM ImagenDdbb i WHERE i.idActividad = :idActividad ORDER BY i.fechaSubida DESC",
                ImagenDdbb.class);
        query.setParameter("idActividad", idActividad);
        return query.getResultList();
     }

    @Override
    public List<ImagenDdbb> findByTipo(TipoImagen tipo){
        TypedQuery<ImagenDdbb> query = entityManager.createQuery(
                "SELECT i FROM imagenDdbb i WHERE i.tipo = :tipo ORDER BY i.fechaSubida DESC",
                ImagenDdbb.class);
        query.setParameter("tipo", tipo);
        return query.getResultList();
    }

    @Override
    public Long countByIdUsuario(Integer idUsuario){
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(i) FROM ImagenDdbb i WHERE i.idUsuario = :idUsuario", Long.class);
        query.setParameter("idUsuario", idUsuario);
        return query.getSingleResult();
    }

    @Override
    public Boolean existsByIdBiciAndTipo(Integer idBici, TipoImagen tipo){
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(i) FROM ImagenDdbb i WHERE idBici : idBici AND i.tipo = :tipo", Long.class);
        query.setParameter("idBici", idBici);
        query.setParameter("tipo", tipo);
        return query.getSingleResult() > 0;
    }

    @Override
    public void deleteByIdUsuario(Integer idUsuario){
        entityManager.createQuery("DELETE FROM imagenDdbb i WHERE i.idUsuario = :idUsuario")
                .setParameter("idUsuario", idUsuario)
                .executeUpdate();
    }

    @Override
    public Optional<ImagenDdbb> findFirstImagenByIdBici(Integer idBici){
        try{
            TypedQuery<ImagenDdbb> query = entityManager.createQuery(
                    "SELECT i FROM ImagenDdbb i WHERE i.idBici = :idBici ORDER BY i.fechaSubida ASC",
                    ImagenDdbb.class);
            query.setParameter("idBici", idBici);
            query.setMaxResults(1);
            return Optional.of(query.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }
    }
}
