package spring.interfaces.impl;


import spring.interfaces.TournoiDao;
import spring.models.Tournoi;

public class TournoiDaoImpl implements TournoiDao {

    private final TournoiMetierImpl tournoiMetier;

    public TournoiDaoImpl(TournoiMetierImpl tournoiMetier) {
        this.tournoiMetier = tournoiMetier;
    }



    @Override
    public int calculerdureeEstimeeTournoi(Long tournoiId) {
        Tournoi tournoi = tournoiMetier.trouverParId(tournoiId)
                .orElseThrow(() -> new IllegalArgumentException("Tournoi not found"));
        int nombreEquipes = tournoi.getEquipes().size();
        int dureeMoyenneMatch = tournoi.getJeu().getDureeMoyenne();
        int tempsPause = tournoi.getTempsPause();
        return (nombreEquipes * dureeMoyenneMatch) + tempsPause;

    }
}
