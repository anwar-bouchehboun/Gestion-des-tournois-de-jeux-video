package spring.interfaces.impl;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import spring.models.Joueur;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JoueurTestUntaire {

    private JoueurImp joueurImp;

    @Before
    public void setUp() {
        joueurImp = new JoueurImp();
    }

  /*  @Test
    public void testCreer() {
        Joueur joueur = new Joueur();
        joueur.setId(1L);
        joueur.setAge(12);
        joueur.setPseudo("anwar");
        
        Joueur result = joueurImp.creer(joueur);

        assertNotNull(result);
        assertEquals(joueur.getId(), result.getId());
    }

    @Test
    public void testModifier() {
        Joueur joueur = new Joueur();
        joueur.setId(1L);
        
        joueurImp.creer(joueur); // Créer d'abord le joueur

        Joueur result = joueurImp.modifier(joueur);

        assertNotNull(result);
        assertEquals(joueur.getId(), result.getId());
        assertTrue(entityManager.getLogMessages().contains("Joueur modifier Succes1"));
    }

    @Test
    public void testSupprimer() {
        Long id = 1L;
        Joueur joueur = new Joueur();
        joueur.setId(id);
        
        joueurImp.creer(joueur); // Créer d'abord le joueur

        joueurImp.supprimer(id);

        assertTrue(entityManager.getLogMessages().contains("joueur supprimée avec l'ID:  " + id));
    }

    @Test
    public void testTrouverParId() {
        Long id = 1L;
        Joueur joueur = new Joueur();
        joueur.setId(id);
        
        joueurImp.creer(joueur); // Créer d'abord le joueur

        Optional<Joueur> result = joueurImp.trouverParId(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    public void testTrouverTous() {
        joueurImp.creer(new Joueur()); // Ajouter un joueur

        List<Joueur> result = joueurImp.trouverTous();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    // Classe fictive pour simuler le comportement de l'EntityManager
    private class FakeEntityManager extends EntityManager {
        private List<Joueur> joueurs = new ArrayList<>();
        private List<String> logMessages = new ArrayList<>();

        @Override
        public void persist(Object entity) {
            if (entity instanceof Joueur) {
                joueurs.add((Joueur) entity);
            }
        }

        @Override
        public Joueur find(Class<Joueur> entityClass, Long id) {
            return joueurs.stream().filter(j -> j.getId().equals(id)).findFirst().orElse(null);
        }

        @Override
        public void remove(Object entity) {
            if (entity instanceof Joueur) {
                joueurs.remove(entity);
            }
        }

        @Override
        public Query createQuery(String qlString) {
            return new FakeQuery(joueurs);
        }

        public List<String> getLogMessages() {
            return logMessages;
        }

        // Méthode pour simuler les messages de log
        public void log(String message) {
            logMessages.add(message);
        }
    }

    // Classe fictive pour simuler le comportement d'une requête
    private class FakeQuery extends Query {
        private List<Joueur> joueurs;

        public FakeQuery(List<Joueur> joueurs) {
            this.joueurs = joueurs;
        }

        @Override
        public List<Joueur> getResultList() {
            return new ArrayList<>(joueurs);
        }

        // Implémentez d'autres méthodes nécessaires si besoin
    }*/
}
