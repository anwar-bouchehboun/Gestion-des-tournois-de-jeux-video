package spring.interfaces.impl;

import spring.interfaces.GeneralInterface;
import spring.interfaces.TournoiMetier;
import spring.interfaces.TournoiDao;
import spring.models.Tournoi;

import java.util.List;
import java.util.Optional;

public class TournoiMetierImpl implements TournoiMetier , GeneralInterface<Tournoi> {


    public TournoiMetierImpl() {
    }

    @Override
    public int obtenirdureeEstimeeTournoi(Long tournoiId) {
        return 0;
    }

    @Override
    public Tournoi creer(Tournoi entity) {
        return null;
    }

    @Override
    public Tournoi modifier(Tournoi entity) {
        return null;
    }

    @Override
    public void supprimer(Long id) {

    }

    @Override
    public Optional<Tournoi> trouverParId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Tournoi> trouverTous() {
        return null;
    }
}
