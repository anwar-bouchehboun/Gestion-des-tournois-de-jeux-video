package spring.interfaces.impl;

import spring.interfaces.GeneralInterface;
import spring.models.Joueur;
import spring.utilis.EntityManagerSingleton;
import spring.utilis.LoggerMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class JoueurImp implements GeneralInterface<Joueur> {


     public JoueurImp(){

     }


    @Override
    public Joueur creer(Joueur entity) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            if (em.contains(entity)) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }
            transaction.commit();
            LoggerMessage.info("Joueur Ajouter Succes" + entity.getId());

        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Error Jouter :" +e.getMessage());
        } finally {
            EntityManagerSingleton.closeEntityManagerFactory();
        }
        return entity;
    }

    @Override
    public Joueur modifier(Joueur entity) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(entity);
            transaction.commit();
            LoggerMessage.info("Joueur modifier Succes" + entity.getId());

        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Error Jouter :" +e.getMessage());

        } finally {
            em.close(); 
        }
        return entity;
    }

    @Override
    public void supprimer(Long id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Joueur joueur = em.find(Joueur.class, id);
            if (joueur != null) {
                em.remove(joueur);
                transaction.commit();
                LoggerMessage.info("joueur supprimée avec l'ID:  " + id);
            } else {
                LoggerMessage.warn("Tentative de suppression d'un joueur inexistant avec l'ID:  " + id);
            }
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Error Jouter :" +e.getMessage());

        } finally {
            EntityManagerSingleton.closeEntityManagerFactory();
        }
    }

    @Override
    public Optional<Joueur> trouverParId(Long id) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        try {
            Joueur joueur = entityManager.find(Joueur.class, id);
            return Optional.ofNullable(joueur);
        } finally {
            entityManager.close();
        }


    }

    @Override
    public List<Joueur> trouverTous() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            Query query = em.createQuery("SELECT j FROM Joueur j", Joueur.class); 
            return query.getResultList();
        } finally {
            EntityManagerSingleton.closeEntityManagerFactory();
        }
    }



}
