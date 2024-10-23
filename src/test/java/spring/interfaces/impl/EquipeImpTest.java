package spring.interfaces.impl;

import org.junit.Before;
import org.junit.Test;
import spring.models.Equipe;
import spring.utilis.EntityManagerSingleton;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class EquipeImpTest {

    private EquipeImp equipeImp;
    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManager = EntityManagerSingleton.getEntityManager();
        equipeImp = new EquipeImp(); 
    }

    @Test
    public void testCreer() {
        Equipe equipe = new Equipe();
        equipe.setNom("codesss");
        equipe.setClassement(121);
        equipeImp.creer(equipe);
    }

    @Test
    public void testModifier() {
        Equipe equipe = new Equipe();
        equipe.setId(1L);
        equipeImp.creer(equipe); 
        equipe.setNom("Nouvelle Équipe"); 
        equipeImp.modifier(equipe);
        Equipe found = entityManager.find(Equipe.class, equipe.getId());
        assertEquals("Nouvelle Équipe", found.getNom());
    }

    @Test
    public void testSupprimer() {
        Long id = 1L;
        Equipe equipe = new Equipe();
        equipe.setId(id);
        equipeImp.creer(equipe);
        equipeImp.supprimer(id);
        Equipe found = entityManager.find(Equipe.class, id);
        assertNull(found); 
    }

    @Test
    public void testTrouverParId() {
        Long id = 1L;
        Equipe equipe = new Equipe();
        equipeImp.creer(equipe); 
        Optional<Equipe> result = equipeImp.trouverParId(id);
        assertTrue(result.isPresent());
        assertEquals(equipe.getId(), result.get().getId());
    }

    @Test
    public void testTrouverTous() {
        Equipe equipe1 = new Equipe();
        Equipe equipe2 = new Equipe();
        equipeImp.creer(equipe1);
        equipeImp.creer(equipe2);
        List<Equipe> result = equipeImp.trouverTous();
        assertEquals(2, result.size());
    }
}
