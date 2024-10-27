package spring.services;

import org.springframework.stereotype.Service;
import spring.interfaces.TournoiMetier;
import spring.interfaces.impl.TournoiDaoImpl;
import spring.interfaces.impl.TournoiMetierImpl;
import spring.models.Tournoi;
import spring.utilis.LoggerMessage;

import java.util.List;
import java.util.Optional;

@Service
public class TournoiServices {

    private final TournoiMetierImpl tournoiMetier;

 
    public TournoiServices(TournoiMetierImpl tournoiMetier) {
       this.tournoiMetier=tournoiMetier;
    }

    public Tournoi creerTournoi(Tournoi tournoi) {
        return tournoiMetier.creer(tournoi);
    }

    public Tournoi modifierTournoi(Tournoi tournoi) {
        return tournoiMetier.modifier(tournoi);
    }

    public void supprimerTournoi(Long id) {
        tournoiMetier.supprimer(id);
    }

    public Optional<Tournoi> trouverTournoiParId(Long id) {
        return tournoiMetier.trouverParId(id);
    }

    public List<Tournoi> trouverTousTournois() {
        return tournoiMetier.trouverTous();
    }

    public void ajouterEquipeAuTournoiParIds(Long tournoiId, Long equipeId) {
      Optional<Tournoi> tournoiOpt = trouverTournoiParId(tournoiId);
        if (tournoiOpt.isPresent()) {
            Tournoi tournoi = tournoiOpt.get();
            tournoiMetier.ajouterEquipeAuTournoi(tournoiId,equipeId);
        } else {
            LoggerMessage.warn("not found");
        }
    }
    
    public void retirerEquipeDuTournoi( Long tournoid,Long equipeId) {
        tournoiMetier.retirer(tournoid,equipeId);
    }

    public int obtenirDureeEstimeeTournoi(Long tournoiId) {
        return tournoiMetier.obtenirdureeEstimeeTournoi(tournoiId);
    }
}
