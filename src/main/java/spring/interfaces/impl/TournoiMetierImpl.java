package spring.interfaces.impl;

import spring.interfaces.GeneralInterface;
import spring.interfaces.TournoiMetier;
import spring.models.Tournoi;
import spring.utilis.EntityManagerSingleton;
import spring.utilis.LoggerMessage;
import spring.models.Equipe;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TournoiMetierImpl implements TournoiMetier, GeneralInterface<Tournoi> {

   // private TournoiDaoExtension tournoiDaoExtension;
    private TournoiDaoImpl tournoiDaoImpl;

    public TournoiMetierImpl() {}

  /*  public void setTournoiDaoExtension(TournoiDaoExtension tournoiDaoExtension) {
        this.tournoiDaoExtension = tournoiDaoExtension;
    }*/


    public void setTournoiDaoImpl(TournoiDaoImpl tournoiDaoImpl) {
        this.tournoiDaoImpl = tournoiDaoImpl;

    }




    @Override
    public int obtenirdureeEstimeeTournoi(Long tournoiId) {
        return tournoiDaoImpl.calculerdureeEstimeeTournoi(tournoiId);
    }

    @Override
    public Tournoi creer(Tournoi entity) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(entity);
            transaction.commit();
            LoggerMessage.info("Tournoi créé avec succès : " + entity.getId());
            return entity;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Erreur lors de la création du tournoi : " + e.getMessage());
            throw e;
        } finally {
            EntityManagerSingleton.closeEntityManager(em);
        }
    }

    @Override
    public Tournoi modifier(Tournoi entity) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Tournoi tournoi = em.merge(entity);
            transaction.commit();
            LoggerMessage.info("Tournoi modifié avec succès : " + tournoi.getId());
            return tournoi;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Erreur lors de la modification du tournoi : " + e.getMessage());
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
            Tournoi tournoi = em.find(Tournoi.class, id);
            if (tournoi != null) {
                em.remove(tournoi);
                transaction.commit();
                LoggerMessage.info("Tournoi supprimé avec succès : " + id);
            } else {
                LoggerMessage.warn("Tentative de suppression d'un tournoi inexistant : " + id);
            }
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Erreur lors de la suppression du tournoi : " + e.getMessage());
            throw e;
        } finally {
            EntityManagerSingleton.closeEntityManager(em);
        }
    }

    @Override
    public Optional<Tournoi> trouverParId(Long id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            Tournoi tournoi = em.find(Tournoi.class, id);
            return Optional.ofNullable(tournoi);
        } finally {
            EntityManagerSingleton.closeEntityManager(em);
        }
    }

    @Override
    public List<Tournoi> trouverTous() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            return em.createQuery("SELECT DISTINCT t FROM Tournoi t LEFT JOIN FETCH t.equipes", Tournoi.class)
                    .getResultList();
        } finally {
            EntityManagerSingleton.closeEntityManager(em);
        }
    }

    public void retirer(Long tournoid,Long equipeId) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Tournoi tournoi = em.find(Tournoi.class, tournoid);
            Equipe equipe = em.find(Equipe.class, equipeId);
            if (equipe != null) {
                tournoi.getEquipes().remove(equipe);
                tournoi.setEquipes(null);
                em.merge(tournoi);
                equipe.getTournois().remove(tournoi);
                equipe.setTournois(null);
                em.merge(equipe);
                transaction.commit();
                LoggerMessage.info("Équipe retirée du tournoi: " + equipeId);
            } else {
                LoggerMessage.warn("Équipe non trouvée ou n'appartient pas au tournoi avec l'ID: " + equipeId);
            }
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Erreur lors du retrait de l'équipe du tournoi : " + e.getMessage());
            throw e;
        } finally {
            EntityManagerSingleton.closeEntityManager(em);
        }
    }

    public void ajouterEquipeAuTournoi(Long tournoiId, Long equipeId) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Tournoi tournoi = em.find(Tournoi.class, tournoiId);
            Equipe equipe = em.find(Equipe.class, equipeId);
            if (tournoi != null && equipe != null) {
                tournoi.getEquipes().add(equipe);
                equipe.getTournois().add(tournoi);
                em.merge(tournoi);
                em.merge(equipe);
                transaction.commit();
                LoggerMessage.info("Équipe " + equipeId + " ajoutée au tournoi " + tournoiId);
            } else {
                LoggerMessage.warn("Tournoi ou équipe non trouvé(e)");
            }
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Erreur lors de l'ajout de l'équipe au tournoi : " + e.getMessage());

        } finally {
            EntityManagerSingleton.closeEntityManager(em);
        }
    }


}
