package spring.interfaces.impl;

import spring.interfaces.GeneralInterface;
import spring.models.Jeu;
import spring.models.Tournoi;
import spring.utilis.EntityManagerSingleton;
import spring.utilis.LoggerMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JeuImp implements GeneralInterface<Jeu> {

    public JeuImp() {

    }

    @Override
    public Jeu creer(Jeu entity) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(entity);
        transaction.commit();
        em.close();
        return entity;
    }

    @Override
    public Jeu modifier(Jeu entity) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Jeu updatedEntity = em.merge(entity);
        transaction.commit();
        em.close();
        return updatedEntity;
    }

    @Override
    public void supprimer(Long id) {
      /*  EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Jeu jeu = em.find(Jeu.class, id);
            if (jeu != null) {
                TypedQuery<Tournoi> query = em.createQuery("SELECT t FROM Tournoi t WHERE t.jeu = :jeu", Tournoi.class);
                query.setParameter("jeu", jeu);
                List<Tournoi> tournois = query.getResultList();
                for (Tournoi tournoi : tournois) {
                    tournoi.setJeu(null);
                    em.merge(tournoi);
                }
                jeu.setTournois(null);
                em.merge(jeu);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
          LoggerMessage.error("Error updating Jeu associations: " + e.getMessage());
        } finally {
            em.close();
        }*/
    }

    @Override
    public Optional<Jeu> trouverParId(Long id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Jeu entity = em.find(Jeu.class, id);
        em.close();
        return Optional.ofNullable(entity);
    }

    @Override
    public List<Jeu> trouverTous() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        List<Jeu> jeux = em.createQuery("SELECT j FROM Jeu j", Jeu.class).getResultList();
        em.close();
        return jeux;
    }
}
