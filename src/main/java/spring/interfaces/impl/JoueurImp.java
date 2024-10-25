package spring.interfaces.impl;

import spring.interfaces.GeneralInterface;
import spring.models.Joueur;
import spring.utilis.EntityManagerSingleton;
import spring.utilis.LoggerMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class JoueurImp implements GeneralInterface<Joueur> {

    public JoueurImp() {
    }

    @Override
    public Joueur creer(Joueur entity) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(entity);
            transaction.commit();
            LoggerMessage.info("Joueur Ajouter Succes" + entity.getId());
            return entity;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Error Jouter :" + e.getMessage());
            throw e;
        } finally {
            EntityManagerSingleton.closeEntityManager(em);
        }
    }

    @Override
    public Joueur modifier(Joueur entity) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            entity = em.merge(entity);
            transaction.commit();
            LoggerMessage.info("Joueur modifier Succes" + entity.getId());
            return entity;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Error Jouter :" + e.getMessage());
            throw e;
        } finally {
            EntityManagerSingleton.closeEntityManager(em);
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
            LoggerMessage.error("Error Jouter :" + e.getMessage());
            throw e;
        } finally {
            EntityManagerSingleton.closeEntityManager(em);
        }
    }

    @Override
    public Optional<Joueur> trouverParId(Long id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            Joueur joueur = em.find(Joueur.class, id);
            return Optional.ofNullable(joueur);
        } finally {
            EntityManagerSingleton.closeEntityManager(em);
        }
    }

    @Override
    public List<Joueur> trouverTous() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            String jpql = "SELECT DISTINCT j FROM Joueur j LEFT JOIN FETCH j.equipe";
            return em.createQuery(jpql, Joueur.class).getResultList();
        } finally {
            EntityManagerSingleton.closeEntityManager(em);
        }
    }
}
