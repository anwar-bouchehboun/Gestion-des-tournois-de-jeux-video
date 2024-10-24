package spring.interfaces.impl;

import org.junit.Before;
import org.junit.Test;
import spring.models.Equipe;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class EquipeImpTest {

    private EquipeImp equipeImp;

    @Before
    public void setUp() {

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
        equipe.setNom("javaAura");
        equipe.setClassement(121);
        equipeImp.creer(equipe); 
        equipe.setNom("Nouvelle Équipe"); 
        equipeImp.modifier(equipe);
    }

    @Test
    public void testSupprimer() {
        Long id = 4L;
        Equipe equipe = new Equipe();
        equipe.setId(id);
        equipeImp.creer(equipe);
        equipeImp.supprimer(id);

    }

    @Test
    public void testTrouverParId() {
        Long id = 1L;

        Optional<Equipe> result = equipeImp.trouverParId(id);
        assertTrue(result.isPresent());
    }

    @Test
    public void testTrouverTous() {

        List<Equipe> result = equipeImp.trouverTous();
        result.stream().forEach(System.out::println);
    }
}
