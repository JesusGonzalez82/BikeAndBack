package com.jesus.bikeandride.Dao;

import com.jesus.bikeandride.Model.Email;
import com.jesus.bikeandride.Model.EmailDdbb;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class EmailDao implements IEmailDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<EmailDdbb> getListEmailByUserId(long id) {
        try {
            String query = "FROM EmailDdbb e WHERE e.user.idUser = :idUser";
            TypedQuery<EmailDdbb> typedQuery = em.createQuery(query, EmailDdbb.class);
            typedQuery.setParameter("idUser", id);
            List<EmailDdbb> list = typedQuery.getResultList();
            return list;
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }

    // Métodos que devuelve True o False

    public boolean emailExistsById(long id){
        try{
            String query = "SELECT COUNT(e) FROM EmailDdbb e WHERE e.email = :email";
            TypedQuery<Long> typedQuery = em.createQuery(query, Long.class);
            typedQuery.setParameter("id", id);
            Long count = typedQuery.getSingleResult();
            return count > 0;
        }catch (Exception e){
            return false;
        }
    }

    public boolean emailExistsByAddress(String emailAddress){
        try{
            String query = "SELECT COUNT(e) FROM EmailDdbb e WHERE e.user.idUser = :idUser";
            TypedQuery<Long> typedQuery = em.createQuery(query, Long.class);
            typedQuery.setParameter("email", emailAddress);
            Long count = typedQuery.getSingleResult();
            return count > 0;
        }catch (Exception e){
            return false;
        }
    }

}


