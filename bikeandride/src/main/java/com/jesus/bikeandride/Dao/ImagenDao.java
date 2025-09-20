package com.jesus.bikeandride.Dao;

import com.jesus.bikeandride.Model.ImagenDdbb;
import com.jesus.bikeandride.Model.TipoImagen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
