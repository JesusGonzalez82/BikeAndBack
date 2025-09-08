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
public class EmailDao implements IEmailDao{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<EmailDdbb> getListEmailByUserId(long id){
        try{
            String query = "FROM EmailDdbb e WHERE e.user.idUser = :idUser";
            TypedQuery<EmailDdbb> typedQuery = em.createQuery(query, EmailDdbb.class);
            typedQuery.setParameter("idUser", id);
            List<EmailDdbb> list = typedQuery.getResultList();
            return list;
        } catch (NoResultException e){
            return Collections.emptyList();
        }
    }

    public EmailDdbb getEmailById(long id){
        try{
            String query = "FROM EmailDdbb e WHERE e.idEmail = :id";
            TypedQuery<EmailDdbb> typedQuery = em.createQuery(query, EmailDdbb.class);
            typedQuery.setParameter("id", id);
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    @Transactional
    public EmailDdbb createEmail(EmailDdbb email){
        em.persist(email);
        em.flush();
        return email;
    }

    @Transactional
    public EmailDdbb updateEmail(EmailDdbb email){
        try{
            EmailDdbb updateEmail = em.merge(email);
            em.flush();
            return updateEmail;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el email " + e.getMessage(), e);
        }
    }

    public EmailDdbb getEmailByAddress(String emailAddress){
        try{
            String query = "FROM emailDdbb e WHERE e.email = :email";
            TypedQuery<EmailDdbb> typedQuery = em.createQuery(query, EmailDdbb.class);
            typedQuery.setParameter("email", emailAddress);
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
