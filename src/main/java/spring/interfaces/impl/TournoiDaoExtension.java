package spring.interfaces.impl;

import spring.interfaces.TournoiDao;
import spring.models.Tournoi;

public class TournoiDaoExtension  implements TournoiDao {

    private final TournoiImp tournoiRepository;

    public TournoiDaoExtension(TournoiImp tournoiRepository) {
        this.tournoiRepository = tournoiRepository;
    }

    @Override
    public int calculerdureeEstimeeTournoi(Long tournoiId) {
        Tournoi tournoi = tournoiRepository.trouverParId(tournoiId)
                .orElseThrow(() -> new IllegalArgumentException("Tournoi not found"));
        int nombreEquipes = tournoi.getEquipes().size();
        int dureeMoyenneMatch = tournoi.getJeu().getDureeMoyenne();
        int difficulte = Integer.parseInt(tournoi.getJeu().getDifficulte());
        int tempsPause = tournoi.getTempsPause();
        int tempsCeremonie = tournoi.getTempsCeremonie();
        return (nombreEquipes * dureeMoyenneMatch * difficulte) + tempsPause + tempsCeremonie;
    }
}
