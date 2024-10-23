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
            return entity;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; 
        } finally {
            EntityManagerSingleton.closeEntityManagerFactory();
        }
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
            return entity;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; 
        } finally {
            em.close(); 
        }
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
            throw e; 
        } finally {
            EntityManagerSingleton.closeEntityManagerFactory();
        }
    }

    @Override
    public Optional<Joueur> trouverParId(Long id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Joueur joueur = em.find(Joueur.class, id);
            transaction.commit();
            return Optional.ofNullable(joueur);
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; 
        } finally {
            EntityManagerSingleton.closeEntityManagerFactory();
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
