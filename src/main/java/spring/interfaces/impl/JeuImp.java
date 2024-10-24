package spring.interfaces.impl;

import spring.interfaces.GeneralInterface;
import spring.models.Jeu;
import spring.utilis.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Jeu entity = em.find(Jeu.class, id);
        if (entity != null) {
            em.remove(entity);
        }
        transaction.commit();
        em.close();
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
