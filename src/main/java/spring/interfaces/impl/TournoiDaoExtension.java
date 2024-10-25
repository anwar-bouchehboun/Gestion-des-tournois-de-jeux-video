package spring.interfaces.impl;

import spring.interfaces.TournoiDao;
import spring.models.Tournoi;

public class TournoiDaoExtension  implements TournoiDao {

    private final TournoiMetierImpl tournoiMetier;

    public TournoiDaoExtension(TournoiMetierImpl tournoiMetier) {
        this.tournoiMetier = tournoiMetier;
    }

    @Override
    public int calculerdureeEstimeeTournoi(Long tournoiId) {
        Tournoi tournoi = tournoiMetier.trouverParId(tournoiId)
                .orElseThrow(() -> new IllegalArgumentException("Tournoi not found"));
        int nombreEquipes = tournoi.getEquipes().size();
        int dureeMoyenneMatch = tournoi.getJeu().getDureeMoyenne();
        int difficulte = Integer.parseInt(tournoi.getJeu().getDifficulte());
        int tempsPause = tournoi.getTempsPause();
        int tempsCeremonie = tournoi.getTempsCeremonie();
        return (nombreEquipes * dureeMoyenneMatch * difficulte) + tempsPause + tempsCeremonie;
    }
}
