package spring.interfaces.impl;

import spring.interfaces.GeneralInterface;
import spring.interfaces.OperationInteraface;
import spring.models.Equipe;
import spring.models.Joueur;
import spring.utilis.EntityManagerSingleton;
import spring.utilis.LoggerMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class EquipeImp implements GeneralInterface<Equipe>, OperationInteraface<Equipe> {

    public EquipeImp() {
    }

    @Override
    public Equipe creer(Equipe entity) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            LoggerMessage.info("Equipe Ajouter Succes" + entity.getId());
            return entity;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Error Debuge : " + e);
        } finally {
            entityManager.close();
        }
        return entity;
    }

    @Override
    public Equipe modifier(Equipe entity) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
            LoggerMessage.info("Equipe modifer Succes" + entity.getId());
            return entity;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Error Debuge : " + e);
        } finally {
            entityManager.close();
        }
        return entity;
    }

    @Override
    public void supprimer(Long id) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Equipe equipe = entityManager.find(Equipe.class, id);
            if (equipe != null) {
                entityManager.remove(equipe);
                LoggerMessage.info("Equipe supprimée avec l'ID:  " + id);
            } else {
                LoggerMessage.warn("Tentative de suppression d'une équipe inexistante avec l'ID:  " + id);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Error Debuge : " + e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Equipe> trouverParId(Long id) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        try {
            Equipe equipe = entityManager.find(Equipe.class, id);
            return Optional.ofNullable(equipe);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Equipe> trouverTous() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            List<Equipe> query = em.createQuery("SELECT e FROM Equipe e", Equipe.class).getResultList();
            return query;
        } finally {
            em.close();
        }
    }

    @Override
    public void ajouter(Long joueurId, Equipe equipe) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Joueur joueur = entityManager.find(Joueur.class, joueurId);
            if (joueur != null) {
                joueur.setEquipe(equipe);
                entityManager.merge(joueur);
                LoggerMessage.info("Joueur ajouté à l'équipe: " + joueurId);
            } else {
                LoggerMessage.warn("Joueur non trouvé avec l'ID: " + joueurId);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Error Debuge : " + e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void retirer(Long joueurId, Equipe equipe) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Joueur joueur = entityManager.find(Joueur.class, joueurId);
            if (joueur != null ) {
                joueur.setEquipe(null);
                entityManager.merge(equipe);
                LoggerMessage.info("Joueur retiré de l'équipe: " + joueurId);
            } else {
                LoggerMessage.warn("Joueur non trouvé ou n'appartient pas à l'équipe avec l'ID: " + joueurId);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Error Debuge : " + e);
        } finally {
            entityManager.close();
        }
    }

}
