package com.jesus.bikeandride.Dao;

import com.jesus.bikeandride.Model.UserDdbb;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {
    @PersistenceContext
    EntityManager em;

    // Buscamos un usuario por ID
    public UserDdbb getUserById(long userId){
        try{
            return em.find(UserDdbb.class, userId);
        }catch (Exception e){
            return null;
        }
    }
    @Transactional
    public UserDdbb createUser(UserDdbb user){
        em.persist(user);
        em.flush();
        return user;
    }

    @Transactional
    public UserDdbb updateUser(UserDdbb user){
        try{
            UserDdbb updateUser = em.merge(user); //actualizamos solo si el registro existe
            em.flush();
            return updateUser;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage(), e);
        }
    }
}
