package spring.interfaces.impl;


import spring.interfaces.TournoiDao;
import spring.models.Tournoi;

public class TournoiDaoImpl implements TournoiDao {

    private final TournoiImp tournoiRepository;

    public TournoiDaoImpl(TournoiImp tournoiRepository) {
        this.tournoiRepository = tournoiRepository;
    }



    @Override
    public int calculerdureeEstimeeTournoi(Long tournoiId) {
        Tournoi tournoi = tournoiRepository.trouverParId(tournoiId)
                .orElseThrow(() -> new IllegalArgumentException("Tournoi not found"));
        int nombreEquipes = tournoi.getEquipes().size();
        int dureeMoyenneMatch = tournoi.getJeu().getDureeMoyenne();
        int tempsPause = tournoi.getTempsPause();
        return (nombreEquipes * dureeMoyenneMatch) + tempsPause;

    }
}
