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

    @Test
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
        
        joueurImp.creer(joueur);

        Joueur result = joueurImp.modifier(joueur);

        assertNotNull(result);
        assertEquals(joueur.getId(), result.getId());
    }

    @Test
    public void testSupprimer() {
        Long id = 1L;
        Joueur joueur = new Joueur();
        joueur.setId(id);
        
        joueurImp.creer(joueur); // Créer d'abord le joueur

        joueurImp.supprimer(id);

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
        joueurImp.creer(new Joueur());

        List<Joueur> result = joueurImp.trouverTous();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }




    }



