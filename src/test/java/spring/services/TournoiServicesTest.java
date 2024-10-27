package spring.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spring.interfaces.impl.TournoiMetierImpl;
import spring.models.Tournoi;
import spring.models.Jeu;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TournoiServicesTest {

    @Mock
    private TournoiMetierImpl tournoiMetier;

    private TournoiServices tournoiServices;
    private Tournoi tournoi;
    private Jeu jeu;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tournoiServices = new TournoiServices(tournoiMetier);
        
        // Création d'un tournoi de test
        tournoi = new Tournoi();

        tournoi.setTitre("Test Tournament");
        tournoi.setDateDebut(LocalDate.now());
        tournoi.setDateFin(LocalDate.now().plusDays(7));
        tournoi.setNombreSpectateurs(100);
        tournoi.setDureeEstimee(120);
        tournoi.setTempsPause(15);
        tournoi.setTempsCeremonie(30);
        tournoi.setStatut("PLANIFIE");
    }

    @Test
    public void testCreerTournoi() {
        when(tournoiMetier.creer(any(Tournoi.class))).thenReturn(tournoi);
        
        Tournoi result = tournoiServices.creerTournoi(tournoi);
        
        assertNotNull(result);
        assertEquals(tournoi.getId(), result.getId());
        assertEquals(tournoi.getTitre(), result.getTitre());
        verify(tournoiMetier).creer(tournoi);
    }

    @Test
    public void testModifierTournoi() {
        when(tournoiMetier.modifier(any(Tournoi.class))).thenReturn(tournoi);
        
        Tournoi result = tournoiServices.modifierTournoi(tournoi);
        
        assertNotNull(result);
        assertEquals(tournoi.getId(), result.getId());
        verify(tournoiMetier).modifier(tournoi);
    }

    @Test
    public void testTrouverTournoiParId() {
        when(tournoiMetier.trouverParId(1L)).thenReturn(Optional.of(tournoi));
        
        Optional<Tournoi> result = tournoiServices.trouverTournoiParId(1L);
        
        assertTrue(result.isPresent());
        assertEquals(tournoi.getId(), result.get().getId());
        verify(tournoiMetier).trouverParId(1L);
    }

    @Test
    public void testTrouverTousTournois() {
        List<Tournoi> tournois = Arrays.asList(tournoi);
        when(tournoiMetier.trouverTous()).thenReturn(tournois);
        
        List<Tournoi> result = tournoiServices.trouverTousTournois();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(tournoiMetier).trouverTous();
    }

    @Test
    public void testAjouterEquipeAuTournoiParIds() {
        when(tournoiMetier.trouverParId(1L)).thenReturn(Optional.of(tournoi));
        doNothing().when(tournoiMetier).ajouterEquipeAuTournoi(1L, 2L);
        
        tournoiServices.ajouterEquipeAuTournoiParIds(1L, 2L);
        
        verify(tournoiMetier).trouverParId(1L);
        verify(tournoiMetier).ajouterEquipeAuTournoi(1L, 2L);
    }

    @Test
    public void testObtenirDureeEstimeeTournoi() {
        when(tournoiMetier.obtenirdureeEstimeeTournoi(1L)).thenReturn(120);
        
        int result = tournoiServices.obtenirDureeEstimeeTournoi(1L);
        
        assertEquals(120, result);
        verify(tournoiMetier).obtenirdureeEstimeeTournoi(1L);
    }
}
