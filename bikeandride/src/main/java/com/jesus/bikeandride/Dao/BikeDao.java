package com.jesus.bikeandride.Dao;

import com.jesus.bikeandride.Model.BikeDdbb;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository

public class BikeDao implements IBikeDao{
    @PersistenceContext
    EntityManager em;

    @Override
    public List<BikeDdbb> getListBikeByUserId(long userId) {
        try {
            String query = "FROM BikeDdbb b WHERE b.user.idUser = :idUser";
            TypedQuery<BikeDdbb> typedQuery = em.createQuery(query,BikeDdbb.class);
            typedQuery.setParameter("idUser", userId);
            List<BikeDdbb> list = typedQuery.getResultList();
            return list;

        } catch (NoResultException e) {
            return Collections.emptyList();
        }

    }

    public BikeDdbb getBikeById(long id){
        try{
            String query = "FROM BikeDdbb b WHERE b.id = :id";
            TypedQuery<BikeDdbb> typedQuery = em.createQuery(query, BikeDdbb.class);
            typedQuery.setParameter("id", id);
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Transactional
    public BikeDdbb createBike(BikeDdbb bike){
        em.persist(bike);
        em.flush();
        return bike;
    }

    @Transactional
    public BikeDdbb updateBike(BikeDdbb bike){
        try{
            BikeDdbb updateBike = em.merge(bike);
            em.flush();
            return  updateBike;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar la bici: " + e.getMessage(), e);
        }
    }

/*
    public class BikeDao implements IUserDao {


        @Override
        @ExcludeFromLogging
        public User getUserByName(String nameUser) {
            try {
                String query = "FROM User u WHERE u.name = " + "'" + nameUser + "'";
                TypedQuery<User> typedQuery = em.createQuery(query, User.class);
                return typedQuery.getSingleResult();
            } catch (NoResultException e) {
                return new User(0L);
            }
        }

        @Override
        @ExcludeFromLogging
        @Transactional
        public User getUserById(Long id) {
            try {
                String query = "FROM User u WHERE u.idUser = " + id;
                TypedQuery<User> typedQuery = em.createQuery(query, User.class);
                return typedQuery.getSingleResult();
            } catch (NoResultException e) {
                return new User(0L);
            }
        }

*/

    }
