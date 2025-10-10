package com.jesus.bikeandride.Dao;

import com.jesus.bikeandride.Model.EmailDdbb;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
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

    public boolean emailExistsById(long id) {
        try {
            String query = "SELECT COUNT(e) FROM EmailDdbb e WHERE e.idEmail = :id";
            TypedQuery<Long> typedQuery = em.createQuery(query, Long.class);
            typedQuery.setParameter("id", id);
            Long count = typedQuery.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean emailExistsByAddress(String emailAddress) {
        try {
            String query = "SELECT COUNT(e) FROM EmailDdbb e WHERE e.email = :email";
            TypedQuery<Long> typedQuery = em.createQuery(query, Long.class);
            typedQuery.setParameter("email", emailAddress);
            Long count = typedQuery.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean userHasEmails(long userId) {
        try {
            String query = "SELECT COUNT(e) FROM EmailDdbb e WHERE e.user.idUser = :idUser";
            TypedQuery<Long> typedQuery = em.createQuery(query, Long.class);
            typedQuery.setParameter("idUser", userId);
            Long count = typedQuery.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean emailBelongsToUser(long emailId, long userId) {
        try {
            String query = "SELECT COUNT(e) FROM EmailDdbb e WHERE e.idEmail = :emailId AND e.user.idUser = :userId";
            TypedQuery<Long> typedQuery = em.createQuery(query, Long.class);
            typedQuery.setParameter("emailId", emailId);
            typedQuery.setParameter("userId", userId);
            Long count = typedQuery.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // MÉTODOS CRUD

    @Transactional
    public boolean createEmail(EmailDdbb email) {
        try {
            em.persist(email);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updateEmail(EmailDdbb email) {
        try {
            em.merge(email);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean deleteEmailById(long id) {
        try {
            EmailDdbb email = getEmailByIdInternal(id);
            if (email != null) {
                em.remove(email);
                em.flush();
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean deleteEmailByAddress(String emailAddress) {
        try {
            String query = "DELETE FROM EmailDdbb e WHERE e.email = :email";
            Query typedQuery = em.createQuery(query); // Corregido: sin tipo específico
            typedQuery.setParameter("email", emailAddress);
            int deletedCount = typedQuery.executeUpdate();
            return deletedCount > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // Métodos Auxiliares para uso interno

    private EmailDdbb getEmailByIdInternal(long id){
        try{
            String query = "FROM EmailDdbb e WHERE e.idEmail = :id";
            TypedQuery<EmailDdbb> typedQuery = em.createQuery(query, EmailDdbb.class);
            typedQuery.setParameter("id", id);
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
<<<<<<< Updated upstream
=======
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
            String query = "FROM EmailDdbb e WHERE e.email = :email";
            TypedQuery<EmailDdbb> typedQuery = em.createQuery(query, EmailDdbb.class);
            typedQuery.setParameter("email", emailAddress);
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public boolean emailExists(String email) {
        try {
            Long count = em.createQuery(
                            "SELECT COUNT(e) FROM EmailDdbb e WHERE e.email = :email",
                            Long.class
                    ).setParameter("email", email)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public EmailDdbb getEmailByUserId(Long userId){
        try {
            List<EmailDdbb> emails = getListEmailByUserId(userId);
            return emails.isEmpty() ? null : emails.get(0);
        }catch (Exception e){
            return null;
        }
    }

>>>>>>> Stashed changes
}


